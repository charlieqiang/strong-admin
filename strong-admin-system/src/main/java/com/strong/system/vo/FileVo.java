package com.strong.system.vo;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2025/3/28 15:21
 **/
public class FileVo implements Serializable {
    private static final long serialVersionUID = 1811817189558782042L;

    // 资源地址
    private String url;
    // 文件路径
    private String path;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
