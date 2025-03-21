package com.strong.system.service;

import com.strong.system.vo.MenuVo;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/19 12:39
 **/
public interface MenuService {

    /**
     * 获取所有菜单
     *
     * @return
     */
    List<MenuVo> queryAllMenuList();

    /**
     * 批量更新菜单
     *
     * @param menuVoList
     */
    void updateMenuList(List<MenuVo> menuVoList);
}
