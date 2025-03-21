package com.strong.system.controller;

import com.strong.api.security.dto.LoginUserDto;
import com.strong.api.security.service.ApiSecurityService;
import com.strong.common.entity.result.PageResult;
import com.strong.common.entity.result.Result;
import com.strong.common.exception.CustomizeException;
import com.strong.system.param.UserParam;
import com.strong.system.service.UserService;
import com.strong.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Result<UserVo> getUserById(@PathVariable String id) {
        UserVo user = userService.getUserById(id);
        return Result.success(user);
    }

    @PostMapping("")
    public Result addUser(@RequestBody UserParam userParam) {
        userService.addUser(userParam);
        return Result.success();
    }

    @GetMapping("/info")
    public Result<UserVo> getUserInfo() {
        LoginUserDto loginUser = apiSecurityService.getLoginUser();
        if (loginUser == null) {
            throw new CustomizeException("查无用户信息");
        }
        UserVo userInfoVo = userService.getUserInfoById(loginUser.getUserId());
        return Result.success(userInfoVo);
    }

    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result getUserPage(@RequestBody UserParam userParam,
                                        @PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("pageSize") Integer pageSize) {
        PageResult userPage = userService.getUserPage(userParam, pageNum, pageSize);
        return Result.success(userPage);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @PutMapping("")
    public Result updateUser(@RequestBody UserParam userParam) {
        userService.updateUser(userParam);
        return Result.success();
    }
}