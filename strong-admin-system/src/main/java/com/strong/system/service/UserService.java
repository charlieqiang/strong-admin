package com.strong.system.service;


import com.strong.common.entity.result.PageResult;
import com.strong.system.entity.User;
import com.strong.system.param.UserParam;
import com.strong.system.vo.UserInfoVo;

/**
 * @author charlie
 * @date 2025/2/13 11:21
 **/
public interface UserService {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 通过account查询用户
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 通过UserId查询用户信息
     *
     * @param userId
     * @return
     */
    UserInfoVo getUserInfoById(String userId);

    /**
     * 通过参数查询用户分页
     *
     * @param userParam
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult getUserPage(UserParam userParam, Integer pageNum, Integer pageSize);

    /**
     * 添加用户
     *
     * @param userParam
     * @return
     */
    UserInfoVo addUser(UserParam userParam);
}
