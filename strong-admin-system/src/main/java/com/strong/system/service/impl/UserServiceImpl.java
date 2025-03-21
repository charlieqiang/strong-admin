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
import com.strong.system.vo.UserRoleVo;
import com.strong.system.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public UserVo getUserById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        User user = userMapper.getUserById(id);
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public User getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        return userMapper.getUserByAccount(account);
    }

    @Override
    public UserVo getUserInfoById(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new CustomizeException("用户获取失败：userId缺失");
        }
        UserVo user = getUserById(userId);
        List<String> roles = userMapper.getUserRolesById(userId);
        user.setRoles(roles);
        return user;
    }

    @Override
    public PageResult getUserPage(UserParam userParam, Integer pageNum, Integer pageSize) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUserPage(user);
        List<UserVo> userInfoVoList = buildUserInfoVoList(userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return PageUtil.getPageResult(pageInfo, userInfoVoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
        user.setPassword(apiSecurityService.encryptPassword(userParam.getPassword()));
        userMapper.addUser(user);

        List<UserRole> userRoleList = new ArrayList<>();
        for (String roleId : userParam.getRoleIdList()) {
            UserRole userRole = new UserRole();
            userRole.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        if (!CollectionUtils.isEmpty(userRoleList)) {
            userMapper.addUserRoleBatch(userRoleList);
        }
    }

    private List<UserVo> buildUserInfoVoList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        List<UserVo> userInfoVoList = new ArrayList<>();
        List<String> userIdList = userList.stream().map(User::getId).collect(Collectors.toList());
        List<UserRoleVo> userRoleList = userMapper.getUserRolesByUserIdList(userIdList);
        Map<String, List<UserRoleVo>> userRolesMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            userRolesMap = userRoleList.stream().collect(Collectors.groupingBy(UserRoleVo::getUserId));
        }
        for (User user : userList) {
            UserVo userInfoVo = new UserVo();
            userInfoVo.setId(user.getId());
            userInfoVo.setAccount(user.getAccount());
            userInfoVo.setUsername(user.getUsername());
            userInfoVo.setAvatar(user.getAvatar());
            if (userRolesMap.containsKey(user.getId())) {
                List<UserRoleVo> userRoleVos = userRolesMap.get(user.getId());
                userInfoVo.setRoles(userRoleVos.stream().map(UserRoleVo::getRoleCode).collect(Collectors.toList()));
            }
            userInfoVoList.add(userInfoVo);
        }
        return userInfoVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String id) {
        if (StringUtils.isBlank(id)) {
            throw new CustomizeException("删除用户失败：id缺失");
        }
        userMapper.deleteUserById(id);
        userMapper.deleteUserRoleByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserParam userParam) {
        if (StringUtils.isBlank(userParam.getId())) {
            throw new CustomizeException("更新用户失败：id缺失");
        }
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        if (StringUtils.isNotBlank(userParam.getPassword())) {
            user.setPassword(apiSecurityService.encryptPassword(userParam.getPassword()));
        }
        userMapper.updateUser(user);

        if (!CollectionUtils.isEmpty(userParam.getRoleIdList())) {
            userMapper.deleteUserRoleByUserId(userParam.getId());
            List<UserRole> userRoleList = new ArrayList<>();
            for (String roleId : userParam.getRoleIdList()) {
                UserRole userRole = new UserRole();
                userRole.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
                userRole.setUserId(userParam.getId());
                userRole.setRoleId(roleId);
                userRoleList.add(userRole);
            }
            userMapper.addUserRoleBatch(userRoleList);
        }
    }
}
