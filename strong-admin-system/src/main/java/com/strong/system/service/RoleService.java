package com.strong.system.service;

import com.strong.system.vo.RoleVo;
import com.strong.system.vo.MenuVo;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 13:21
 **/
public interface RoleService {
    /**
     * 获取所有角色列表
     *
     * @return
     */
    List<RoleVo> queryAllRoles();

    /**
     * 更新角色
     *
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 新增角色
     *
     * @param roleVo
     * @return
     */
    RoleVo addRole(RoleVo roleVo);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteRole(String roleId);
}
