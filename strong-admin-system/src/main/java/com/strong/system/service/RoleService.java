package com.strong.system.service;

import com.strong.system.vo.RoleVo;

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
}
