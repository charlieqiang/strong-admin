package com.strong.system.entity;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author yusl
 */
public class UserRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6407389234802007574L;

    private String id;
    private String userId;
    private String roleId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
