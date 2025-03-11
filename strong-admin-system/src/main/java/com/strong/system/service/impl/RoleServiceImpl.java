package com.strong.system.service.impl;

import com.strong.system.mapper.RoleMapper;
import com.strong.system.service.RoleService;
import com.strong.system.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 13:21
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleVo> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }
}
