package com.strong.system.entity;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author charlie
 */
public class Menu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5981348276088754986L;

    private String name;
    private String path;
    private String parentId;
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
