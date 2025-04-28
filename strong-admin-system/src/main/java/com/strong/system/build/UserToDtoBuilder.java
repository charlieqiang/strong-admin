package com.strong.system.build;

import com.strong.api.system.dto.UserDto;
import com.strong.system.entity.User;

/**
 * @author charlie
 * @date 2025/4/28 13:11
 **/
public class UserToDtoBuilder {
    private UserToDtoBuilder() {
        // 私有构造器，工具类不允许实例化
    }

    public static UserDto build(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setAccount(user.getAccount());
        dto.setAvatar(user.getAvatar());
        dto.setDeptId(user.getDeptId());
        dto.setPhone(user.getPhone());
        dto.setHiredDate(user.getHiredDate());
        dto.setUseStatus(user.getUseStatus());
        return dto;
    }
}
