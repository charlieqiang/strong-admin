package com.strong.common.file.oss.aliyun;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @author charlie
 */
@Component
@ConditionalOnClass(OSSClient.class)
public class AliyunFileOssClient extends AliyunOssGenerator {

    private String baseFolder;

    public AliyunFileOssClient(AliyunOssProperties ossProperties) {
        super(ossProperties.getAccessKeyId(),ossProperties.getAccessKeySecret(),
                ossProperties.getEndpoint(),ossProperties.getBucket());
        this.baseFolder = ossProperties.getBaseFolder();
    }

    public String getAssertFilePath(String key){
        return getOssFilePath(key);
    }

    @Override
    public String folder() {
        return baseFolder;
    }
}
