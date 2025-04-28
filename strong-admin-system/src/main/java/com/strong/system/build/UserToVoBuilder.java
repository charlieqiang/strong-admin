package com.strong.system.build;

import com.strong.api.system.dto.UserDto;
import com.strong.system.entity.User;
import com.strong.system.vo.UserVo;

import java.util.List;

/**
 * @author charlie
 * @date 2025/4/28 13:11
 **/
public class UserToVoBuilder {
    private UserToVoBuilder() {
        // 私有构造器，工具类不允许实例化
    }

    public static UserVo build(User user) {
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setAccount(user.getAccount());
        userVo.setUsername(user.getUsername());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }
}
