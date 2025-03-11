package com.strong.system.entity;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author yusl
 */
public class RoleMenu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5368672979779468811L;

    private String id;
    private String roleId;
    private String menuId;

}
