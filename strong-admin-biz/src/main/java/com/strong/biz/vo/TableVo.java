package com.strong.biz.vo;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2025/3/17 11:07
 **/
public class TableVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6509061808216663515L;

    private String title;
    // 'published','draft','deleted'
    private String status;
    private String author;
    private String display_time;
    private String pageviews;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(String display_time) {
        this.display_time = display_time;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }
}
