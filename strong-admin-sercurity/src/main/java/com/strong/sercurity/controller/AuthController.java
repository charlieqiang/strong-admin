package com.strong.sercurity.controller;

import com.strong.common.entity.result.Result;
import com.strong.sercurity.entity.LoginUser;
import com.strong.sercurity.param.LoginParam;
import com.strong.sercurity.service.LoginService;
import com.strong.sercurity.service.TokenService;
import com.strong.sercurity.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie
 * @date 2025/2/11 13:16
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public Result<TokenVo> login(@RequestBody LoginParam loginParam) {
        LoginUser loginUser = loginService.login(loginParam.getAccount(),
                loginParam.getPassword(),
                loginParam.getCaptchaCode(),
                loginParam.getCaptchaId());
        String token = tokenService.createToken(loginUser);
        return Result.success(new TokenVo(token));
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.success();
    }
}
