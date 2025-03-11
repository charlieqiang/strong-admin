package com.strong.system.mapper;

import com.strong.system.vo.RouteVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author charlie
 * @date 2025/3/11 15:08
 **/
@Mapper
public interface MenuMapper {

    /**
     * 查询所有父级菜单
     *
     * @return
     */
    List<RouteVo> queryParents();

    /**
     * 通过父级菜单id查询所有子菜单
     * @param parentId
     * @return
     */
    List<RouteVo> queryChildrenByParentId(String parentId);
}
