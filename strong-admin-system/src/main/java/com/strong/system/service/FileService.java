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
     * @return fileUrl
     */
    String upload(MultipartFile file);

    /**
     * 附件下载
     *
     * @param date 日期
     * @param filename 文件名
     * @param response HTTP响应对象
     */
    void download(String date, String filename, HttpServletResponse response);
}
