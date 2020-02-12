package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.CityDO;
import com.whu.lysl.entity.dbobj.DistrictDO;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dbobj.ProvinceDO;
import com.whu.lysl.entity.dto.City;
import com.whu.lysl.entity.dto.District;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.Province;
import com.whu.lysl.entity.vo.InstitutionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/11 23:37
 * @Description:
 */
public class RegionConverter {

    public static Province provinceDo2Model(ProvinceDO provinceDO) {
        if (provinceDO == null) {
            return null;
        }

        Province province = new Province();
        province.setProvinceId(provinceDO.getProvinceId());
        province.setProvinceName(provinceDO.getProvinceName());
        province.setProvinceCreateTime(provinceDO.getProvinceCreateTime());
        province.setProvinceModifyTime(provinceDO.getProvinceModifyTime());
        province.setProvinceCode(provinceDO.getProvinceCode());
        province.setCityList(batchCityDo2Model(provinceDO.getCityList()));
        province.setCity(cityDo2Model(provinceDO.getCity()));

        return province;
    }
    public static List<Province> batchProvinceDo2Model(List<ProvinceDO> provinceDOS) {
        List<Province> provinces = new ArrayList<>();
        if(provinceDOS == null) {
            return provinces;
        }
        for (ProvinceDO provinceDO: provinceDOS) {
            provinces.add(provinceDo2Model(provinceDO));
        }
        return provinces;
    }


    public static City cityDo2Model(CityDO cityDO) {
        if (cityDO == null) {
            return null;
        }

        City city = new City();
        city.setCityId(cityDO.getCityId());
        city.setCityName(cityDO.getCityName());
        city.setProvinceId(cityDO.getProvinceId());
        city.setCityCreateTime(cityDO.getCityCreateTime());
        city.setCityModifyTime(cityDO.getCityModifyTime());
        city.setCityCode(cityDO.getCityCode());
        city.setDistrictList(batchDistrictDo2Model(cityDO.getDistrictDOList()));
        city.setDistrict(districtDo2Model(cityDO.getDistrict()));

        return city;
    }
    public static List<City> batchCityDo2Model(List<CityDO> cityDOS) {
        List<City> citys = new ArrayList<>();
        if(cityDOS == null) {
            return citys;
        }
        for (CityDO cityDO: cityDOS) {
            citys.add(cityDo2Model(cityDO));
        }
        return citys;
    }

    public static District districtDo2Model(DistrictDO districtDO) {
        if (districtDO == null) {
            return null;
        }

        District district = new District();
        district.setDistrictId(districtDO.getDistrictId());
        district.setCityId(districtDO.getCityId());
        district.setDistrictCode(districtDO.getDistrictCode());
        district.setDistrictCreateTime(districtDO.getDistrictCreateTime());
        district.setDistrictModifyTime(districtDO.getDistrictModifyTime());
        district.setDistrictName(districtDO.getDistrictName());

        return district;
    }

    public static List<District> batchDistrictDo2Model(List<DistrictDO> districtDOS) {
        List<District> districts = new ArrayList<>();
        if(districtDOS == null) {
            return districts;
        }
        for (DistrictDO districtDO: districtDOS) {
            districts.add(districtDo2Model(districtDO));
        }
        return districts;
    }

}
