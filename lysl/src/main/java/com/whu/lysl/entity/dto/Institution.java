package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 机构模型类
 * @author Visionary
 * @since 2020/2/8 8:36 PM
 */
@Data
public class Institution {

    private Integer id;

    private Date gmtCreated;

    private Date gmtModified;

    private String name;

    private String province;

    private String city;

    private String region;

    private String address;

    private String status;

    private String auth;

}
