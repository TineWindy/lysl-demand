package com.whu.lysl.entity.dto;

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
public class Province {

    private int provinceId;

    private String provinceName;

    private Date provinceCreateTime;

    private Date provinceModifyTime;

    private String provinceCode;

    private List<City> cityList;

    private City city;



}
