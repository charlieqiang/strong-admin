package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.system.service.RoleService;
import com.strong.system.vo.RoleVo;
import com.strong.system.vo.RouteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 11:36
 **/
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public Result<List<RoleVo>> getRoles() {
        List<RoleVo> roleVoList = roleService.queryAllRoles();
        return Result.success(roleVoList);
    }

    @GetMapping("/routes")
    public Result<List<RouteVo>> getRoutes() {
        List<RouteVo> routeVoList = roleService.queryAllRoutes();
        return Result.success(routeVoList);
    }
}
