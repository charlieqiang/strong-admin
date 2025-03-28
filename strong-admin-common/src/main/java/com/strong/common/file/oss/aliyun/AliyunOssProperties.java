package com.strong.common.file.oss.aliyun;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author charlie
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@ConditionalOnClass(OSSClient.class)
public class AliyunOssProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucket;
    private String baseFolder;
    private String assertHost;
    private String callbackUrl;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public String getAssertHost() {
        return assertHost;
    }

    public void setAssertHost(String assertHost) {
        this.assertHost = assertHost;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
