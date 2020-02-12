package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *省级行政区划表
 *
 * @author Tang
 * @since 2018/12/11
 */
@Data
public class ProvinceDO {

    private int provinceId;

    private String provinceName;

    private Date provinceCreateTime;

    private Date provinceModifyTime;

    private String provinceCode;

    private List<CityDO> cityList;

    private CityDO city;



}
