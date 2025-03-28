package com.strong.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.strong.common.exception.CustomizeException;
import com.strong.common.file.oss.aliyun.AliyunFileOssClient;
import com.strong.system.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author charlie
 * @date 2025/3/26 11:32
 **/
@Service
public class FileServiceImpl implements FileService {
    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private AliyunFileOssClient aliyunFileOssClient;

    @Override
    public String uploadToAliyun(MultipartFile file) {
        String filename = file.getOriginalFilename();
        // 获取后缀
        String suffix = FileUtil.getSuffix(filename);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        String uploadFilename = currentDate + "/" + IdUtil.simpleUUID() + "." + suffix;
        try {
            final InputStream inputStream = file.getInputStream();
            aliyunFileOssClient.uploadOssFile(uploadFilename, inputStream);
        } catch (Exception e) {
            log.error("文件上传出现异常，文件名：[{}]，异常信息：[{}]", filename, e.getMessage(), e);
            throw new CustomizeException("文件上传异常，请联系管理员");
        }
        return aliyunFileOssClient.getAssertFilePath(uploadFilename);
    }

    @Override
    public void downloadFromAliyun(String date, String filename, HttpServletResponse response) {
        String key = date + "/" + filename;
        try (InputStream inputStream = aliyunFileOssClient.getOssFileInputStream(key)) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("文件下载失败，文件路径：[{}]，异常信息：[{}]", key, e.getMessage(), e);
            throw new CustomizeException("文件下载失败，请联系管理员");
        }
    }

    @Override
    public String convertPathToUrl(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return "";
        }
        try {
            // 设置签名URL过期时间为3600秒（1小时）。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(aliyunFileOssClient.getBucketName(), filePath);
            request.setExpiration(expiration);
            URL url = aliyunFileOssClient.generatePresignedUrl(request);
            return url.toString();
        } catch (Exception e) {
            log.error("文件地址签名失败", e);
            return "";
        }
    }
}
