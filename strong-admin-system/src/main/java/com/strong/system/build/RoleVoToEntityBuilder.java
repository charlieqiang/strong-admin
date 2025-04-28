package com.strong.system.build;

import com.strong.system.entity.Role;
import com.strong.system.vo.RoleVo;

/**
 * @author charlie
 * @date 2025/4/28 17:59
 **/
public class RoleVoToEntityBuilder {

    private RoleVoToEntityBuilder() {
        // 私有构造器，不允许实例化
    }

    public static Role build(RoleVo roleVo) {
        if (roleVo == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleVo.getId());
        role.setName(roleVo.getName());
        role.setCode(roleVo.getCode());
        role.setDescription(roleVo.getDescription());
        return role;
    }
}
