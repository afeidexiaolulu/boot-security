package com.mytest.security.model;

import lombok.Data;

/**
 * @author
 * @version 1.00
 * @time 2020/2/20 0020  下午 2:22
 */
@Data
public class PermissionDto {

    private Integer id;

    private String code;

    private String description;

    private String url;

}