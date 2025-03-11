package com.strong.system.entity;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author charlie
 */
public class Dept extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3552346244517532581L;

    private String id;
    private String parentId;
    private String name;
    private String leaderUserId;
    private Integer sort;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderUserId() {
        return leaderUserId;
    }

    public void setLeaderUserId(String leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
