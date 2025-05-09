package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.system.service.RoleService;
import com.strong.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 11:36
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result<List<RoleVo>> getRoles() {
        List<RoleVo> roleVoList = roleService.queryAllRoles();
        return Result.success(roleVoList);
    }

    @PutMapping("")
    public Result updateRole(@RequestBody RoleVo roleVo) {
        roleService.updateRole(roleVo);
        return Result.success();
    }

    @PostMapping("")
    public Result<RoleVo> addRole(@RequestBody RoleVo roleVo) {
        RoleVo roleResult = roleService.addRole(roleVo);
        return Result.success(roleResult);
    }

    @DeleteMapping("/{roleId}")
    public Result deleteRole(@PathVariable String roleId) {
        roleService.deleteRole(roleId);
        return Result.success();
    }
}
