package com.strong.system.mapper;

import com.strong.system.entity.User;
import com.strong.system.entity.UserRole;
import com.strong.system.vo.UserRoleVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author charlie
 * @date 2025/2/13 11:24
 **/
@Mapper
public interface UserMapper {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 通过acount查询用户
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 通过userId查询roles
     * @param id
     * @return
     */
    List<String> getUserRolesById(String id);

    /**
     * 批量查询用户角色
     *
     * @param userIdList 用户ID列表
     * @return Map<用户ID, 角色列表>
     */
    List<UserRoleVo> getUserRolesByUserIdList(List<String> userIdList);

    /**
     * 通过角色删除用户角色关系
     *
     * @param roleId
     */
    void deleteUserRoleByRoleId(String roleId);

    /**
     * 通过用户参数查询用户分页
     *
     * @param user
     * @return
     */
    List<User> getUserPage(User user);

    /**
     * 新增用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 添加用户角色
     *
     * @param userRoleList
     */
    void addUserRoleBatch(List<UserRole> userRoleList);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(String id);

    /**
     * 删除用户角色关联关系
     *
     * @param userId
     */
    void deleteUserRoleByUserId(String userId);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(User user);
}
