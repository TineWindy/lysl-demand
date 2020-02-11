package com.whu.lysl.service.region;

import com.whu.lysl.entity.dto.City;
import com.whu.lysl.entity.dto.District;
import com.whu.lysl.entity.dto.Province;

import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/11 22:52
 * @Description:
 */
public interface RegionService {

    /**
     *  query region by pigsProvinceId, pigsCityId and pigsDistrictId
     *
     * @param pigsProvinceId
     * @param pigsCityId
     * @param pigsDistrictId
     * @return
     */
    Province queryRegion(int pigsProvinceId, int pigsCityId, int pigsDistrictId);

    /**
     * get all province
     *
     * @return
     */
    List<Province> getProvinceList();

    /**
     * get all city list by provinceId
     * @param provinceId
     * @return
     */
    List<City> getCityListByProvinceId(int provinceId);

    /**
     *  get all district list by cityId
     *
     * @param cityId
     * @return
     */
    List<District> getDistrictListByCityId(int cityId);

    City getCityByCode(String cityCode);

    Province getProvinceByCode(String provinceCode);

    Province getProvinceByName(String provinceName);

    City getCityByProvinceCityName(int provinceId, String cityName);
}
