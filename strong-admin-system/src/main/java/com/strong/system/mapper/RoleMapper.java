package com.strong.system.mapper;

import com.strong.system.entity.Role;
import com.strong.system.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 13:29
 **/
@Mapper
public interface RoleMapper {

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> queryAllRoles();

    /**
     * 新增角色
     *
     * @param role
     */
    void insertRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteById(String roleId);
}
