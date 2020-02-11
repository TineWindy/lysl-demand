package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *  城市行政表
 *
 * @author Tang
 * @since 2018/12/11
 */
@Data
public class CityDO {

    private int cityId;

    private String cityName;

    private int provinceId;

    private Date cityCreateTime;

    private Date cityModifyTime;

    private String cityCode;

    private List<DistrictDO> districtDOList;

    private DistrictDO district;
    
}
