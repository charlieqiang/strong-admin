package com.strong.system.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author charlie
 * @date 2025/3/26 11:31
 **/
public interface FileService {
    /**
     * 附件上传
     *
     * @param file
     * @return filePath
     */
    String uploadToAliyun(MultipartFile file);

    /**
     * 附件下载
     *
     * @param date 日期
     * @param filename 文件名
     * @param response HTTP响应对象
     */
    void downloadFromAliyun(String date, String filename, HttpServletResponse response);

    /**
     * 通过文件路径获取实际资源地址
     *
     * @param filePath
     * @return
     */
    String convertPathToUrl(String filePath);
}
