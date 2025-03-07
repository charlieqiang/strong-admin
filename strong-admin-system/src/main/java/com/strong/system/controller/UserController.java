package com.strong.system.controller;

import com.strong.api.security.dto.LoginUserDto;
import com.strong.api.security.service.ApiSecurityService;
import com.strong.api.system.dto.UserDto;
import com.strong.common.entity.result.Result;
import com.strong.system.entity.User;
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

    @Autowired
    private ApiSecurityService apiSecurityService;

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @GetMapping("/info")
    public Result<UserInfoVo> getUserInfo() {
        LoginUserDto loginUser = apiSecurityService.getLoginUser();
        UserDto user = loginUser.getUserDto();
        UserInfoVo userInfoVo = new UserInfoVo(user);
        return Result.success(userInfoVo);
    }
}