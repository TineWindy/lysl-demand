package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * 机构do
 * @author Visionary
 * @since 2020/2/8 8:28 PM
 */
@Data
public class InstitutionDO {

    private Integer id;

    private Date gmtCreated;

    private Date gmtModified;

    private String name;

    private String province;

    private String city;

    private String address;

    private String status;

    private String auth;

}
