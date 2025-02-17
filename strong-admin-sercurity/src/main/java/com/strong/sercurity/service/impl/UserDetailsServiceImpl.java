package com.strong.sercurity.service.impl;

import com.strong.sercurity.entity.LoginUser;
import com.strong.system.entity.User;
import com.strong.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    // @Autowired
    // private PasswordService passwordService;
    //
    // @Autowired
    // private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // User user = userService.getUserByAccount(username);
        // if (StringUtils.isBlank(user)) {
        //     log.info("登录用户：{} 不存在.", username);
        //     throw new ServiceException(MessageUtils.message("user.not.exists"));
        // } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
        //     log.info("登录用户：{} 已被删除.", username);
        //     throw new ServiceException(MessageUtils.message("user.password.delete"));
        // } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
        //     log.info("登录用户：{} 已被停用.", username);
        //     throw new ServiceException(MessageUtils.message("user.blocked"));
        // }
        //
        // passwordService.validate(user);
        //
        // return createLoginUser(user);
        return null;
    }

    // public UserDetails createLoginUser(User user) {
    //     // return new LoginUser(user.getId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    // }
}
