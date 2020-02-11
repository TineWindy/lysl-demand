package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * 地区行政区划表
 *
 * @author Tang
 * @since 2018/12/11
 *
 */
@Data
public class DistrictDO {

    private int districtId;

    private int cityId;

    private String districtName;

    private Date districtCreateTime;

    private Date districtModifyTime;

    private String districtCode;


}
