package com.whu.lysl.entity.vo;

import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dbobj.UserDO;
import lombok.Data;

@Data
public class DemandVO {

    //institution模块信息
    private String institutionType;

    private String institutionName;

    private String province;

    private String city;

    private String district;

    private String address;

    private String auth;

    //user模块信息
    private String doneeName;

    private String phone;

    private String wxNumber;

    //demand模块信息
    private String demandId;

    private String materials;

    private String description;

    public DemandVO(InstitutionDO institutionDO, UserDO userDO, DemandDO demandDO){
        if (institutionDO != null) {
            institutionName = institutionDO.getName();
            institutionType = "hospital";
            province = institutionDO.getProvince();
            city = institutionDO.getCity();
            district = institutionDO.getDistrict();
            address = institutionDO.getAddress();
            auth = institutionDO.getAuth();
        }

        if (userDO != null) {
            doneeName = userDO.getName();
            phone = userDO.getPhone();
            wxNumber = userDO.getWxNumber();
        }

        if(demandDO != null) {
            demandId = demandDO.getDemandId();
            addMaterial(demandDO);
            description = demandDO.getDescription();
        }
    }

    public void addMaterial(DemandDO demandDO){
        if (materials == null)
            materials = "";
        materials += "{" +
                "\"materialName\": \"" + demandDO.getMaterialName() + "\"," +
                "\"materialId\": " + demandDO.getMaterialId() + "," +
                "\"materialNum\": " + demandDO.getMaterialNum() +
                "},";
    }

    public void encapsulation(){
        materials = "[" + materials.substring(0, materials.length()-1) + "]";
    }
}
