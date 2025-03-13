package com.strong.system.service.impl;

import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.system.entity.Role;
import com.strong.system.entity.RoleMenu;
import com.strong.system.mapper.MenuMapper;
import com.strong.system.mapper.RoleMapper;
import com.strong.system.mapper.UserMapper;
import com.strong.system.service.RoleService;
import com.strong.system.vo.RoleVo;
import com.strong.system.vo.RouteVo;
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
        List<RoleVo> roleVoList = roleMapper.queryAllRoles();
        if (CollectionUtils.isEmpty(roleVoList)) {
            return roleVoList;
        }
        for (RoleVo roleVo : roleVoList) {
            List<RouteVo> parents = menuMapper.queryParentsByRoleId(roleVo.getId());
            if (CollectionUtils.isEmpty(parents)) {
                roleVo.setRoutes(new ArrayList<>());
                continue;
            }
            for (RouteVo routeVo : parents) {
                setChildrenByRoleId(routeVo, roleVo.getId());
            }
            roleVo.setRoutes(parents);
        }
        return roleVoList;
    }

    private void setChildrenByRoleId(RouteVo routeVo, String roleId) {
        if (routeVo == null) {
            return;
        }
        List<RouteVo> children = menuMapper.queryChildrenByParentIdAndRoleId(routeVo.getId(), roleId);
        if (children == null) {
            return;
        }
        routeVo.setChildren(children);
        for (RouteVo child : children) {
            setChildrenByRoleId(child, roleId);
        }
    }

    @Override
    public List<RouteVo> queryAllRoutes() {
        List<RouteVo> parents = menuMapper.queryParents();
        if (CollectionUtils.isEmpty(parents)) {
            return parents;
        }
        for (RouteVo routeVo : parents) {
            setChildren(routeVo);
        }
        return parents;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleVo roleVo) {
        Role role = new Role();
        role.setId(roleVo.getId());
        role.setName(roleVo.getName());
        role.setCode(roleVo.getKey());
        role.setDescription(roleVo.getDescription());
        roleMapper.updateRole(role);
        menuMapper.deleteRoleMenuByRoleId(roleVo.getId());
        generateRoleMenuRelation(roleVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleVo addRole(RoleVo roleVo) {
        Role role = new Role();
        role.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
        role.setName(roleVo.getName());
        role.setCode(roleVo.getKey());
        role.setDescription(roleVo.getDescription());
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

    private static List<String> getAllRouteIds(List<RouteVo> routes) {
        if (CollectionUtils.isEmpty(routes)) {
            return new ArrayList<>();
        }
        return routes.stream()
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

    private void setChildren(RouteVo routeVo) {
        if (routeVo == null) {
            return;
        }
        List<RouteVo> children = menuMapper.queryChildrenByParentId(routeVo.getId());
        if (children == null) {
            return;
        }
        routeVo.setChildren(children);
        for (RouteVo child : children) {
            setChildren(child);
        }
    }
}
