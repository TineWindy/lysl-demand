package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user do
 * @author Visionary
 * @since 2020/2/10 3:41 AM
 */
@Data
public class UserDO implements Serializable {

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

    private static final long serialVersionUID = 1L;
}