package com.strong.system.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 13:20
 **/
public class RouteVo implements Serializable {
    private static final long serialVersionUID = -7005586503657704252L;

    private String id;
    private String path;
    private String title;
    private String parentId;
    private List<RouteVo> children;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RouteVo> getChildren() {
        return children;
    }

    public void setChildren(List<RouteVo> children) {
        this.children = children;
    }
}
