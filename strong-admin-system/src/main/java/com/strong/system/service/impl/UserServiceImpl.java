package com.strong.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.strong.api.security.service.ApiSecurityService;
import com.strong.common.entity.result.PageResult;
import com.strong.common.exception.CustomizeException;
import com.strong.common.util.page.PageUtil;
import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.system.entity.User;
import com.strong.system.entity.UserRole;
import com.strong.system.mapper.UserMapper;
import com.strong.system.param.UserParam;
import com.strong.system.service.UserService;
import com.strong.system.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie
 * @date 2025/2/13 11:22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApiSecurityService apiSecurityService;

    @Override
    public User getUserById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        return userMapper.getUserByAccount(account);
    }

    @Override
    public UserInfoVo getUserInfoById(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new CustomizeException("用户获取失败：userId缺失");
        }
        User user = getUserById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setName(user.getUsername());
        userInfoVo.setAvatar(user.getAvatar());
        List<String> roles = userMapper.getUserRolesById(userId);
        userInfoVo.setRoles(roles);
        return userInfoVo;
    }

    @Override
    public PageResult getUserPage(UserParam userParam, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUserPage(userParam);
        List<UserInfoVo> userInfoVoList = buildUserInfoVoList(userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return PageUtil.getPageResult(pageInfo, userInfoVoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoVo addUser(UserParam userParam) {
        userParam.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
        userParam.setPassword(apiSecurityService.encryptPassword(userParam.getPassword()));
        userMapper.addUser(userParam);

        List<UserRole> userRoleList = new ArrayList<>();
        for (String roleId : userParam.getRoleIdList()) {
            UserRole userRole = new UserRole();
            userRole.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
            userRole.setUserId(userParam.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        if (!CollectionUtils.isEmpty(userRoleList)) {
            userMapper.addUserRoleBatch(userRoleList);
        }

        User user = getUserByAccount(userParam.getAccount());
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setName(user.getUsername());
        userInfoVo.setAccount(user.getAccount());
        return userInfoVo;
    }

    private List<UserInfoVo> buildUserInfoVoList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        List<UserInfoVo> userInfoVoList = new ArrayList<>();
        for (User user : userList) {
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setId(user.getId());
            userInfoVo.setAccount(user.getAccount());
            userInfoVo.setName(user.getUsername());
            userInfoVo.setAvatar(user.getAvatar());
            userInfoVoList.add(userInfoVo);
        }
        return userInfoVoList;
    }
}
