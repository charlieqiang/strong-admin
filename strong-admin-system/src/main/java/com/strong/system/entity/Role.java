package com.strong.system.entity;


import com.strong.common.entity.system.BaseEntity;

/**
 * @author charlie
 */
public class Role extends BaseEntity {

    private String name;
    private Integer useStatus;

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
