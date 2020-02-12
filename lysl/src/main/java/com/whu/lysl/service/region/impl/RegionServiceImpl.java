package com.whu.lysl.service.region.impl;

import com.whu.lysl.base.converters.RegionConverter;
import com.whu.lysl.dao.RegionDAO;
import com.whu.lysl.entity.dto.City;
import com.whu.lysl.entity.dto.District;
import com.whu.lysl.entity.dto.Province;
import com.whu.lysl.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/11 22:54
 * @Description:
 */

@Service
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionDAO regionDAO;

    @Override
    public Province queryRegion(int provinceId, int cityId, int districtId) {

        if (provinceId < 1 || provinceId < 1 || provinceId < 1) {
            return null;
        }
        Province province = RegionConverter.provinceDo2Model(regionDAO.getProvinceDOByProvinceId(provinceId));
        if (province == null) {
            return null;
        }
        City city = RegionConverter.cityDo2Model(regionDAO.getCityDOByCityId(provinceId, provinceId));
        if (city == null) {
            return null;
        }
        District district = RegionConverter.districtDo2Model(regionDAO.getDistrictDOByCityId(cityId, districtId));
        if (district == null) {
            return null;
        }
        city.setDistrict(district);
        province.setCity(city);
        return province;
    }

    @Override
    public List<Province> getProvinceList() {
        return RegionConverter.batchProvinceDo2Model(regionDAO.getProvinceDOList());
    }

    @Override
    public List<City> getCityListByProvinceId(int provinceId) {
        return RegionConverter.batchCityDo2Model(regionDAO.getCityDOListByProvinceId(provinceId));
    }

    @Override
    public List<District> getDistrictListByCityId(int cityId) {
        return RegionConverter.batchDistrictDo2Model(regionDAO.getDistrictDOListByCityId(cityId));
    }

    public City getCityByCode(String cityCode) {
        return RegionConverter.cityDo2Model(this.regionDAO.getCityDOByCode(cityCode));
    }

    public Province getProvinceByCode(String provinceCode) {
        return RegionConverter.provinceDo2Model(this.regionDAO.getProvinceDOByCode(provinceCode));
    }

    public Province getProvinceByName(String provinceName) {
        return RegionConverter.provinceDo2Model(regionDAO.getProvinceDOByName(provinceName));
    }

    public City getCityByProvinceCityName(int provinceId, String cityName) {
        return RegionConverter.cityDo2Model(regionDAO.getCityDOByProvinceIdCityName(provinceId, cityName));
    }
}
