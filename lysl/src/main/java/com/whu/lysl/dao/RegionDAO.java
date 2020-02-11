package com.whu.lysl.dao;

import com.whu.lysl.entity.dbobj.CityDO;
import com.whu.lysl.entity.dbobj.DistrictDO;
import com.whu.lysl.entity.dbobj.ProvinceDO;
import com.whu.lysl.entity.dto.City;
import com.whu.lysl.entity.dto.District;
import com.whu.lysl.entity.dto.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RegionDAO {

    /**
     * get all province
     *
     * @return
     */
    List<ProvinceDO> getProvinceDOList();

    /**
     * get all city list by provinceId
     *
     * @param provinceId
     * @return
     */
    List<CityDO> getCityDOListByProvinceId(@Param("provinceId") int provinceId);

    /**
     * get district list by cityId
     *
     * @param cityId
     * @return
     */
    List<DistrictDO> getDistrictDOListByCityId(@Param("cityId") int cityId);

    /**
     * get province by provinceId
     *
     * @param provinceId
     * @return
     */
    ProvinceDO getProvinceDOByProvinceId(@Param("provinceId") int provinceId);

    /**
     * get city by provinceId and cityId
     *
     * @param provinceId
     * @param cityId
     * @return
     */
    CityDO getCityDOByCityId(@Param("provinceId") int provinceId, @Param("cityId") int cityId);

    /**
     * get district by cityId and districtId
     *
     * @param cityId
     * @param districtId
     * @return
     */
    DistrictDO getDistrictDOByCityId(@Param("cityId") int cityId, @Param("districtId") int districtId);

    CityDO getCityDOByCode(String cityCode);

    ProvinceDO getProvinceDOByCode(String provinceCode);

    ProvinceDO getProvinceDOByName(String provinceName);

    CityDO getCityDOByProvinceIdCityName(@Param("provinceId") int provinceId, @Param("cityName") String cityName);

}