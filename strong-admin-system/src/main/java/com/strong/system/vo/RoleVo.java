package com.strong.system.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 13:19
 **/
public class RoleVo implements Serializable {
    private static final long serialVersionUID = -1896742746858966378L;

    private String id;
    private String key;
    private String name;
    private String description;
    private List<RouteVo> routes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RouteVo> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteVo> routes) {
        this.routes = routes;
    }
}
