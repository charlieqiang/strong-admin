package com.strong.system.mapper;

import com.strong.system.entity.User;
import com.strong.system.param.UserParam;
import com.strong.system.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
     * @param userId
     * @return
     */
    List<String> getUserRolesById(String userId);

    /**
     * 通过角色删除用户角色关系
     *
     * @param roleId
     */
    void deleteUserRoleByRoleId(String roleId);

    /**
     * 通过用户参数查询用户分页
     *
     * @param userParam
     * @return
     */
    List<User> getUserPage(UserParam userParam);
}
