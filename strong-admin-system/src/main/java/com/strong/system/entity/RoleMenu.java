package com.strong.system.entity;

import com.strong.common.entity.system.BaseEntity;

/**
 * @author charlie
 */
public class RoleMenu extends BaseEntity {

    private String id;
    private String roleId;
    private String menuId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
