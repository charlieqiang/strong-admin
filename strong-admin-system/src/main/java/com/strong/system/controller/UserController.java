package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.common.entity.security.LoginUser;
import com.strong.common.util.sercurity.SecurityUtils;
import com.strong.common.entity.system.User;
import com.strong.system.service.UserService;
import com.strong.system.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie
 * @date 2025/2/13 10:03
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @GetMapping("/info")
    public Result<UserInfoVo> getUserInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = new UserInfoVo(user);
        return Result.success(userInfoVo);
    }
}