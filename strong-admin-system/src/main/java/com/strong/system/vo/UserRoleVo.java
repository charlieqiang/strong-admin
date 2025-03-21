package com.strong.system.vo;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author charlie
 */
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = -1835812530369471217L;

    private String userId;
    private String roleCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
