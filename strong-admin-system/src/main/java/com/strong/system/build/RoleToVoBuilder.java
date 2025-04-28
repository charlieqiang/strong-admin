package com.strong.system.build;

import com.strong.system.entity.Role;
import com.strong.system.vo.RoleVo;

/**
 * @author charlie
 * @date 2025/4/28 17:57
 **/
public class RoleToVoBuilder {

    private RoleToVoBuilder() {
        // 私有构造器，不允许实例化
    }

    public static RoleVo build(Role role) {
        if (role == null) {
            return null;
        }
        RoleVo roleVo = new RoleVo();
        roleVo.setId(role.getId());
        roleVo.setName(role.getName());
        roleVo.setCode(role.getCode());
        roleVo.setDescription(role.getDescription());
        return roleVo;
    }
}