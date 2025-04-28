package com.strong.system.build;

import com.strong.system.entity.Menu;
import com.strong.system.vo.MenuVo;

/**
 * @author charlie
 * @date 2025/4/28 17:41
 **/
public class MenuVoToEntityBuilder {

    private MenuVoToEntityBuilder() {
        // 私有构造器，禁止实例化
    }

    public static Menu build(MenuVo menuVo) {
        if (menuVo == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(menuVo.getId());
        menu.setPath(menuVo.getPath());
        menu.setSort(menuVo.getSort());
        menu.setParentId(menuVo.getParentId());
        menu.setName(menuVo.getTitle());
        return menu;
    }
}
