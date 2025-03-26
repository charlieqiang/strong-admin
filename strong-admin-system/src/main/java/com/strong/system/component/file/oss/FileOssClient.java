package com.strong.system.component.file.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @author charlie
 */
@Component
@ConditionalOnClass(OSSClient.class)
public class FileOssClient extends OssGenerator {

    private String assertHost;
    private String baseFolder;

    public FileOssClient(OssProperties ossProperties) {
        super(ossProperties.getAccessKeyId(),ossProperties.getAccessKeySecret(),
                ossProperties.getEndpoint(),ossProperties.getBucket());
        this.assertHost = ossProperties.getAssertHost();
        this.baseFolder = ossProperties.getBaseFolder();
    }

    public String getAssertFileUrl(String key){
        return getOssFileUrl(key);
    }

    @Override
    public String folder() {
        return baseFolder;
    }

    @Override
    public String getOssEndpoint(){
        return assertHost;
    }

}
