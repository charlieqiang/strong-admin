package com.strong.system.mapper;

import com.strong.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author charlie
 * @date 2025/2/13 11:24
 **/
@Mapper
public interface UserMapper {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 通过acount查询用户
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);
}
