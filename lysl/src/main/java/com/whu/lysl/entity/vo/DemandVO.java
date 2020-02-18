package com.whu.lysl.entity.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dbobj.UserDO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> authList = new ArrayList<>();

    //user模块信息
    private String doneeName;

    private String phone;

    private String wxNumber;

    //demand模块信息
    private int demandId;

    private String materials;

    private List<MaterialOrderVO> materialList = new ArrayList<>();

    private String description;

    public DemandVO(InstitutionDO institutionDO, UserDO userDO, DemandDO demandDO){
        institutionName = institutionDO.getName();
        institutionType = "hospital";
        province = institutionDO.getProvince();
        city = institutionDO.getCity();
        district = institutionDO.getDistrict();
        address = institutionDO.getAddress();
        auth = institutionDO.getAuth();
        doneeName = userDO.getName();
        phone = userDO.getPhone();
        wxNumber = userDO.getWxNumber();
        demandId = Integer.valueOf(demandDO.getDemandId());
        description = demandDO.getDescription();

        addMaterial(demandDO);
        addAuth(institutionDO.getAuth());
    }

    public void addMaterial(DemandDO demandDO){
        if (materials == null)
            materials = "";
        materials += "{" +
                "\"materialName\": \"" + demandDO.getMaterialName() + "\"," +
                "\"materialId\": " + demandDO.getMaterialId() + "," +
                "\"materialNum\": " + demandDO.getMaterialNum() +
                "},";

        MaterialOrderVO materialOrderVO = new MaterialOrderVO();
        materialOrderVO.setMaterialName(demandDO.getMaterialName());
        materialOrderVO.setMaterialAmount(demandDO.getMaterialNum());
        materialOrderVO.setMaterialId(demandDO.getMaterialId());
        this.materialList.add(materialOrderVO);
    }

    private void addAuth(String auth) {
        JSONArray jsonArray = new JSONArray();
        if (StringUtils.isNotEmpty(auth)) {
            jsonArray = JSON.parseArray(auth);
        }
        this.authList = jsonArray.toJavaList(String.class);
    }

    public void encapsulation(){
        materials = "[" + materials.substring(0, materials.length()-1) + "]";
    }
}
