package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.system.entity.User;
import com.strong.system.service.UserService;
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
@RequestMapping("/public/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }
}