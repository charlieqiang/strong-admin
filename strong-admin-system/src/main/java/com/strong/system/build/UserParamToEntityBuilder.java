package com.strong.system.build;

import com.strong.system.entity.User;
import com.strong.system.param.UserParam;

/**
 * @author charlie
 * @date 2025/4/28 18:07
 **/
public class UserParamToEntityBuilder {

    private UserParamToEntityBuilder() {
        // 私有构造器，禁止实例化
    }

    public static User build(UserParam userParam) {
        if (userParam == null) {
            return null;
        }
        User user = new User();
        user.setId(userParam.getId());
        user.setUsername(userParam.getUsername());
        user.setPassword(userParam.getPassword());
        user.setAccount(userParam.getAccount());
        user.setAvatar(userParam.getAvatar());
        return user;
    }
}
