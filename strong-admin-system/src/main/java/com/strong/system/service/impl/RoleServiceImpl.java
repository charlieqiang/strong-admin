package com.strong.system.service.impl;

import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.system.build.MenuToVoBuilder;
import com.strong.system.build.RoleToVoBuilder;
import com.strong.system.build.RoleVoToEntityBuilder;
import com.strong.system.entity.Menu;
import com.strong.system.entity.Role;
import com.strong.system.entity.RoleMenu;
import com.strong.system.mapper.MenuMapper;
import com.strong.system.mapper.RoleMapper;
import com.strong.system.mapper.UserMapper;
import com.strong.system.service.RoleService;
import com.strong.system.vo.RoleVo;
import com.strong.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author charlie
 * @date 2025/3/11 13:21
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<RoleVo> queryAllRoles() {
        List<Role> roleList = roleMapper.queryAllRoles();
        if (CollectionUtils.isEmpty(roleList)) {
            return new ArrayList<>();
        }

        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roleList) {
            RoleVo roleVo = RoleToVoBuilder.build(role);
            roleVoList.add(roleVo);
            List<Menu> parentMenuList = menuMapper.queryParentsByRoleId(role.getId());
            if (CollectionUtils.isEmpty(parentMenuList)) {
                roleVo.setRoutes(new ArrayList<>());
                continue;
            }
            List<MenuVo> menuVos = new ArrayList<>();
            for (Menu parentMenu : parentMenuList) {
                MenuVo menuVo = MenuToVoBuilder.build(parentMenu);
                menuVos.add(menuVo);
            }
            for (MenuVo menuVo : menuVos) {
                setChildrenByRoleId(menuVo, roleVo.getId());
            }
            roleVo.setRoutes(menuVos);
        }
        return roleVoList;
    }

    private void setChildrenByRoleId(MenuVo menuVo, String roleId) {
        if (menuVo == null) {
            return;
        }
        List<Menu> menuList = menuMapper.queryChildrenByParentIdAndRoleId(menuVo.getId(), roleId);
        if (CollectionUtils.isEmpty(menuList)) {
            return;
        }
        List<MenuVo> children = new ArrayList<>();
        for (Menu menu : menuList) {
            MenuVo child = MenuToVoBuilder.build(menu);
            children.add(child);
        }
        menuVo.setChildren(children);
        for (MenuVo child : children) {
            setChildrenByRoleId(child, roleId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleVo roleVo) {
        Role role = RoleVoToEntityBuilder.build(roleVo);
        roleMapper.updateRole(role);
        menuMapper.deleteRoleMenuByRoleId(roleVo.getId());
        generateRoleMenuRelation(roleVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleVo addRole(RoleVo roleVo) {
        Role role = RoleVoToEntityBuilder.build(roleVo);
        roleMapper.insertRole(role);
        roleVo.setId(role.getId());
        generateRoleMenuRelation(roleVo);
        return roleVo;
    }

    private void generateRoleMenuRelation(RoleVo roleVo) {
        List<String> menuIdList = getAllRouteIds(roleVo.getRoutes());
        if (!CollectionUtils.isEmpty(menuIdList)) {
            insertRoleMenus(roleVo.getId(), menuIdList);
        }
    }

    public void insertRoleMenus(String roleId, List<String> menuIdList) {
        List<RoleMenu> roleMenuList = menuIdList.stream().map(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            return roleMenu;
        }).collect(Collectors.toList());

        menuMapper.insertByRoleId(roleId, roleMenuList);
    }

    private List<String> getAllRouteIds(List<MenuVo> menuVos) {
        if (CollectionUtils.isEmpty(menuVos)) {
            return new ArrayList<>();
        }
        return menuVos.stream()
                .flatMap(route -> Stream.concat(
                        Stream.of(route.getId()),
                        getAllRouteIds(route.getChildren()).stream()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(String roleId) {
        userMapper.deleteUserRoleByRoleId(roleId);
        roleMapper.deleteById(roleId);
        menuMapper.deleteRoleMenuByRoleId(roleId);
    }
}
