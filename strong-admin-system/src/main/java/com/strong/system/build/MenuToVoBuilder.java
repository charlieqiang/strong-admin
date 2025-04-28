package com.strong.system.build;

import com.strong.system.entity.Menu;
import com.strong.system.vo.MenuVo;

/**
 * @author charlie
 * @date 2025/4/28 17:38
 **/
public class MenuToVoBuilder {

    private MenuToVoBuilder() {
        // 私有构造器，禁止实例化
    }

    public static MenuVo build(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuVo menuVo = new MenuVo();
        menuVo.setId(menu.getId());
        menuVo.setPath(menu.getPath());
        menuVo.setSort(menu.getSort());
        menuVo.setTitle(menu.getName());
        menuVo.setParentId(menu.getParentId());
        // children 不在这里设置，由外部递归 setChildren
        return menuVo;
    }
}
