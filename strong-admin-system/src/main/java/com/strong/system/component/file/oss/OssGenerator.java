package com.strong.system.component.file.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.AbortMultipartUploadRequest;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListMultipartUploadsRequest;
import com.aliyun.oss.model.MultipartUpload;
import com.aliyun.oss.model.MultipartUploadListing;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.ResponseHeaderOverrides;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OssGenerator {
    private static final Logger log = LoggerFactory.getLogger(OssGenerator.class);
    private String accessKeyId;
    private String secretAccessKey;
    private String ossServer;
    private String bucketName;
    private String ossEndpoint;
    private String bucketOssEndpoint;
    public OSSClient client;

    public OssGenerator(String accessKeyId, String secretAccessKey, String ossServer, String bucketName) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.ossServer = ossServer;
        this.bucketName = bucketName;
        this.ossEndpoint = "https://" + this.ossServer + "/";
        this.bucketOssEndpoint = "https://" + this.bucketName + "." + this.ossServer + "/";
        CredentialsProvider provider = new DefaultCredentialProvider(this.accessKeyId, this.secretAccessKey);
        ClientConfiguration config = new ClientConfiguration();
        this.client = new OSSClient(this.ossEndpoint, provider, config);
    }

    public void setBucketAclPublicReadWrite() {
        this.client.setBucketAcl(this.bucketName, CannedAccessControlList.PublicReadWrite);
    }

    public void setBucketAclPrivate() {
        this.client.setBucketAcl(this.bucketName, CannedAccessControlList.Private);
    }

    public void setBucketAclPublicRead() {
        this.client.setBucketAcl(this.bucketName, CannedAccessControlList.PublicRead);
    }

    public void setBucketAclDefault() {
        this.client.setBucketAcl(this.bucketName, CannedAccessControlList.Default);
    }

    public void uploadOssFile(String key, File uploadFile) throws Exception {
        if (this.isOssFileExist(key)) {
            log.error("上传的文件[" + this.folder() + key + "]在[" + this.bucketOssEndpoint + "]已存在！");
            throw new IllegalStateException("上传的文件[" + this.folder() + key + "]已存在！");
        } else {
            try {
                log.info("正在上传文件[" + this.folder() + key + "]到[" + this.bucketOssEndpoint + "]...");
                int partCount = calPartCount(uploadFile);
                if (partCount <= 1) {
                    this.uploadSmallFile(key, uploadFile);
                } else {
                    this.uploadBigFile(key, uploadFile);
                }

                log.info("上传文件[" + this.folder() + key + "]到[" + this.bucketOssEndpoint + "]结束...");
            } catch (Exception var4) {
                log.error("上传文件[" + this.folder() + key + "]到[" + this.bucketOssEndpoint + "]出错." + var4.getMessage(), var4);
                throw var4;
            }
        }
    }

    public void uploadOssFile(String key, InputStream input) {
        if (this.isOssFileExist(key)) {
            log.error("上传的文件[" + this.folder() + key + "]在[" + this.bucketOssEndpoint + "]已存在！");
            throw new IllegalStateException("上传的文件[" + this.folder() + key + "]已存在！");
        } else {
            try {
                log.info("正在上传文件[" + this.folder() + key + "]到[" + this.bucketOssEndpoint + "]...");
                this.uploadSmallFile(key, input);
                log.info("上传文件[" + this.folder() + key + "]到[" + this.bucketOssEndpoint + "]结束...");
            } catch (Exception var4) {
                log.error("上传文件[" + this.folder() + key + "]到[" + this.bucketOssEndpoint + "]出错." + var4.getMessage(), var4);
                throw var4;
            }
        }
    }

    public InputStream getOssFileInputStream(String key) {
        if (!this.isOssFileExist(key)) {
            log.error("获取文件失败：[" + this.bucketOssEndpoint + "]不存在文件[" + this.folder() + key + "]！");
            throw new IllegalStateException("获取文件失败：文件[" + this.folder() + key + "]不存在！");
        } else {
            OSSObject ossObject = this.client.getObject(this.bucketName, this.folder() + key);
            return ossObject.getObjectContent();
        }
    }

    public void deleteOssFile(String key) {
        if (!this.isOssFileExist(key)) {
            log.error("删除文件失败：[" + this.bucketOssEndpoint + "]不存在文件[" + this.folder() + key + "]！");
            throw new IllegalStateException("删除文件失败：文件[" + this.folder() + key + "]不存在！");
        } else {
            this.client.deleteObject(this.bucketName, this.folder() + key);
            log.info("已从[" + this.bucketOssEndpoint + "]删除文件[" + this.folder() + key + "]");
        }
    }

    public String folder() {
        return "";
    }

    public String getOssEndpoint() {
        return this.bucketOssEndpoint;
    }

    public String getOssFileUrl(String key) {
        return this.getOssEndpoint() + this.folder() + key;
    }

    public String getSecretOssFileUrlByOrgUrl(String orgUrl, String showFileName, int seconds) {
        String key = orgUrl.substring(this.getOssEndpoint().length() + this.folder().length());
        return this.getSecretOssFileUrlByKey(key, showFileName, seconds);
    }

    public String getSecretOssFileUrlByKey(String key, String showFileName, int seconds) {
        Date expiration = new Date(System.currentTimeMillis() + (long) (1000 * seconds));
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(this.bucketName, this.folder() + key, HttpMethod.GET);
        request.setExpiration(expiration);
        ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
        responseHeaders.setContentDisposition("attachment;filename=" + showFileName);
        request.setResponseHeaders(responseHeaders);
        String url = this.client.generatePresignedUrl(request).toString();
        return url;
    }

    public boolean isOssFileExist(String key) {
        try {
            this.client.getObjectMetadata(bucketName, this.folder() + key);
            return true;
        } catch (OSSException e) {
            if ("NoSuchKey".equals(e.getErrorCode())) {
                return false;
            } else {
                throw e;
            }
        } catch (ClientException e) {
            throw e;
        }
    }

    public void copyOssFileBySourceUrl(String sourceUrl, String destinationKey) {
        String sourceKey = sourceUrl.substring(this.getOssEndpoint().length() + this.folder().length());
        this.copyOssFileByKey(sourceKey, destinationKey);
    }

    public void copyOssFileByKey(String sourceKey, String destinationKey) {
        if (!this.isOssFileExist(sourceKey)) {
            log.error("复制文件失败：[" + this.bucketOssEndpoint + "]不存在文件[" + this.folder() + sourceKey + "]！");
            throw new IllegalStateException("复制文件失败：文件[" + this.folder() + sourceKey + "]不存在！");
        } else {
            this.client.copyObject(this.bucketName, this.folder() + sourceKey, this.bucketName, this.folder() + destinationKey);
            log.info("在[" + this.bucketOssEndpoint + "]中复制文件[" + this.folder() + sourceKey + "]并命名为[" + this.folder() + destinationKey + "]");
        }
    }

    private void ensureBucket() throws OSSException, ClientException {
        try {
            this.client.createBucket(this.bucketName);
            log.info("在[" + this.ossServer + "]创建了bucket[" + this.bucketName + "]");
        } catch (ServiceException var2) {
            if (!"BucketAlreadyExists".equals(var2.getErrorCode())) {
                throw var2;
            }
        }

    }

    private void deleteBucket() throws OSSException, ClientException {
        ObjectListing objectListing = this.client.listObjects(this.bucketName);
        List<OSSObjectSummary> listDeletes = objectListing.getObjectSummaries();

        for (int i = 0; i < listDeletes.size(); ++i) {
            String objectName = ((OSSObjectSummary) listDeletes.get(i)).getKey();
            this.client.deleteObject(this.bucketName, objectName);
        }

        ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest(this.bucketName);
        MultipartUploadListing uploadListing = this.client.listMultipartUploads(listMultipartUploadsRequest);
        Iterator var5 = uploadListing.getMultipartUploads().iterator();

        while (var5.hasNext()) {
            MultipartUpload upload = (MultipartUpload) var5.next();
            String key = upload.getKey();
            AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest(this.bucketName, key, upload.getUploadId());
            this.client.abortMultipartUpload(abortMultipartUploadRequest);
        }

        this.client.deleteBucket(this.bucketName);
        log.info("在[" + this.ossServer + "]删除了bucket[" + this.bucketName + "]");
    }

    private void uploadSmallFile(String key, File uploadFile) throws OSSException, ClientException {
        this.client.putObject(this.bucketName, this.folder() + key, uploadFile);
    }

    private void uploadSmallFile(String key, InputStream input) throws OSSException, ClientException {
        this.client.putObject(this.bucketName, this.folder() + key, input);
    }

    private void uploadBigFile(String key, File uploadFile) throws OSSException, ClientException, InterruptedException {
        int partCount = calPartCount(uploadFile);
        if (partCount <= 1) {
            throw new IllegalArgumentException("要上传文件的大小必须大于一个Part的字节数：5242880");
        } else {
            String uploadId = this.initMultipartUpload(key);
            ThreadFactory namedThreadFactory = (new ThreadFactoryBuilder()).setNameFormat("oss-pool-%d").build();
            ExecutorService pool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), namedThreadFactory, new CallerRunsPolicy());
            List<PartETag> eTags = Collections.synchronizedList(new ArrayList());

            for (int i = 0; i < partCount; ++i) {
                long start = 5242880L * (long) i;
                long curPartSize = 5242880L < uploadFile.length() - start ? 5242880L : uploadFile.length() - start;
                pool.execute(new OssGenerator.UploadPartThread(this.client, this.bucketName, this.folder() + key, uploadFile, uploadId, i + 1, 5242880L * (long) i, curPartSize, eTags));
            }

            pool.shutdown();

            while (!pool.isTerminated()) {
                pool.awaitTermination(5L, TimeUnit.SECONDS);
            }

            if (eTags.size() != partCount) {
                throw new IllegalStateException("Multipart上传失败，有Part未上传成功。");
            } else {
                this.completeMultipartUpload(key, uploadId, eTags);
            }
        }
    }

    private static int calPartCount(File f) {
        int partCount = (int) (f.length() / 5242880L);
        if (f.length() % 5242880L != 0L) {
            ++partCount;
        }

        return partCount;
    }

    private String initMultipartUpload(String key) throws OSSException, ClientException {
        InitiateMultipartUploadRequest initUploadRequest = new InitiateMultipartUploadRequest(this.bucketName, this.folder() + key);
        InitiateMultipartUploadResult initResult = this.client.initiateMultipartUpload(initUploadRequest);
        String uploadId = initResult.getUploadId();
        return uploadId;
    }

    private void completeMultipartUpload(String key, String uploadId, List<PartETag> eTags) throws OSSException, ClientException {
        Collections.sort(eTags, new Comparator<PartETag>() {
            public int compare(PartETag arg0, PartETag arg1) {
                return arg0.getPartNumber() - arg1.getPartNumber();
            }
        });
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(this.bucketName, this.folder() + key, uploadId, eTags);
        this.client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    private static class UploadPartThread implements Runnable {
        private File uploadFile;
        private String bucket;
        private String object;
        private long start;
        private long size;
        private List<PartETag> eTags;
        private int partId;
        private OSSClient client;
        private String uploadId;

        UploadPartThread(OSSClient client, String bucket, String object, File uploadFile, String uploadId, int partId, long start, long partSize, List<PartETag> eTags) {
            this.uploadFile = uploadFile;
            this.bucket = bucket;
            this.object = object;
            this.start = start;
            this.size = partSize;
            this.eTags = eTags;
            this.partId = partId;
            this.client = client;
            this.uploadId = uploadId;
        }

        public void run() {
            FileInputStream in = null;

            try {
                in = new FileInputStream(this.uploadFile);
                long amt = in.skip(this.start);
                if (amt == -1L) {
                    throw new RuntimeException(this.uploadFile + ": unexpected EOF");
                }

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(this.bucket);
                uploadPartRequest.setKey(this.object);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(in);
                uploadPartRequest.setPartSize(this.size);
                uploadPartRequest.setPartNumber(this.partId);
                UploadPartResult uploadPartResult = this.client.uploadPart(uploadPartRequest);
                this.eTags.add(uploadPartResult.getPartETag());
            } catch (Exception var14) {
                OssGenerator.log.error(var14.getMessage(), var14);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception var13) {
                    }
                }
            }
        }
    }
}


