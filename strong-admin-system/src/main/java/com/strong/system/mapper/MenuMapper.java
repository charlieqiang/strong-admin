package com.strong.system.mapper;

import com.strong.system.entity.Menu;
import com.strong.system.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 15:08
 **/
@Mapper
public interface MenuMapper {

    /**
     * 更新菜单信息
     *
     * @param menu 菜单信息
     */
    void updateMenu(Menu menu);

    /**
     * 查询所有父级菜单
     *
     * @return
     */
    List<Menu> queryParents();

    /**
     * 通过父级菜单id查询所有子菜单
     *
     * @param parentId
     * @return
     */
    List<Menu> queryChildrenByParentId(String parentId);

    /**
     * 通过角色id查询父级菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> queryParentsByRoleId(String roleId);

    /**
     * 通过父级菜单id及角色id查询所有子菜单
     *
     * @param parentId
     * @param roleId
     * @return
     */
    List<Menu> queryChildrenByParentIdAndRoleId(String parentId, String roleId);

    /**
     * 删除角色菜单联系
     *
     * @param roleId
     */
    void deleteRoleMenuByRoleId(String roleId);

    /**
     * 通过roleId新增RoleMenu关联关系
     *
     * @param roleMenuList
     * @param roleId
     */
    void insertByRoleId(String roleId, List<RoleMenu> roleMenuList);

}
