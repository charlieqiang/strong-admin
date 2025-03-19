package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.system.service.MenuService;
import com.strong.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/19 12:38
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result<List<MenuVo>> getRoutes() {
        List<MenuVo> routeVoList = menuService.queryAllMenuList();
        return Result.success(routeVoList);
    }
}
