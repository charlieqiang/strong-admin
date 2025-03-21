package com.strong.system.service.impl;

import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.system.entity.Menu;
import com.strong.system.mapper.MenuMapper;
import com.strong.system.service.MenuService;
import com.strong.system.vo.MenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/19 12:42
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> queryAllMenuList() {
        List<Menu> parents = menuMapper.queryParents();
        if (CollectionUtils.isEmpty(parents)) {
            return new ArrayList<>();
        }
        List<MenuVo> menuVos = new ArrayList<>();
        for (Menu menu : parents) {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu, menuVo);
            menuVo.setTitle(menu.getName());
            menuVos.add(menuVo);
        }
        for (MenuVo menu : menuVos) {
            setChildren(menu);
        }
        return menuVos;
    }

    private void setChildren(MenuVo menuVo) {
        if (menuVo == null) {
            return;
        }
        List<Menu> menuChildren = menuMapper.queryChildrenByParentId(menuVo.getId());
        if (CollectionUtils.isEmpty(menuChildren)) {
            return;
        }
        List<MenuVo> children = new ArrayList<>();
        for (Menu menuChild : menuChildren) {
            MenuVo menuChildVo = new MenuVo();
            BeanUtils.copyProperties(menuChild, menuChildVo);
            menuChildVo.setTitle(menuChild.getName());
            children.add(menuChildVo);
        }
        menuVo.setChildren(children);
        for (MenuVo child : children) {
            setChildren(child);
        }
    }
    
    @Override
    public void updateMenuList(List<MenuVo> menuVoList) {
        if (CollectionUtils.isEmpty(menuVoList)) {
            return;
        }
        for (MenuVo menuVo : menuVoList) {
            updateMenu(menuVo);
        }
    }

    private void updateMenu(MenuVo menuVo) {
        if (menuVo == null) {
            return;
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        menu.setName(menuVo.getTitle());
        menuMapper.updateMenu(menu);
        
        List<MenuVo> children = menuVo.getChildren();
        if (!CollectionUtils.isEmpty(children)) {
            for (MenuVo child : children) {
                updateMenu(child);
            }
        }
    }
    
    @Override
    public void addMenu(MenuVo menuVo) {
        if (menuVo == null) {
            return;
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        menu.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
        menu.setName(menuVo.getTitle());
        menuMapper.insertMenu(menu);
    }
}
