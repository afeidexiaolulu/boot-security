package com.mytest.security.model;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 5:23
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 当前登录用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public static final String SESSION_USER_KEY = "_user";

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    /**
     * 用户权限
     */
    private Set<String> authorities;
}