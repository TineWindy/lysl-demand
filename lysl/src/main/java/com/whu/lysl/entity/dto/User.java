package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * user dto
 * @author Visionary
 * @since 2020/2/10 5:19 AM
 */
@Data
public class User {

    /** id */
    private Integer id;

    /** 创建时间 */
    private Date gmtCreated;

    /** 更新时间 */
    private Date gmtModified;

    /** 所属机构 */
    private Integer institutionId;

    /** 电话 */
    private String phone;

    /** 微信 */
    private String wxNumber;

    /** 姓名 */
    private String name;

}
