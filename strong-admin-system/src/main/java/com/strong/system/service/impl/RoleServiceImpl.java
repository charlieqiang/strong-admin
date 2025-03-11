package com.strong.system.service.impl;

import com.strong.system.mapper.MenuMapper;
import com.strong.system.mapper.RoleMapper;
import com.strong.system.service.RoleService;
import com.strong.system.vo.RoleVo;
import com.strong.system.vo.RouteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public List<RoleVo> queryAllRoles() {
        return roleMapper.queryAllRoles();
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
