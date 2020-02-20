package com.mytest.security.model;

import lombok.Data;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 5:23
 */
@Data
public class AuthenticationRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
