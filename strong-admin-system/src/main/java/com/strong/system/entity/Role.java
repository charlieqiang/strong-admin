package com.strong.system.entity;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author charlie
 */
public class Role extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4452919719025230278L;

    private String id;
    private String name;
    private Integer useStatus;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
}
