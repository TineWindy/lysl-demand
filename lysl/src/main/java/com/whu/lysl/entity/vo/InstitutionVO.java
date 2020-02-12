package com.whu.lysl.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * inst vo
 * @author Visionary
 * @since 2020/2/11 1:12 AM
 */
@Data
public class InstitutionVO {

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
