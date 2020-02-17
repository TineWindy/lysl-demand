package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * 受赠机构和受赠物资信息
 *
 * @author Jzh
 * @since 2020-02-16 15:44
 **/
@Data
public class InstAndMaterialInfo {

    String instName;
    String address;
    String recipient;
    String tel;
    List<String> materialNameList;
    List<Integer> materialQuantityList;
    int matchOrderId;
    String donorName;
    String donorPhone;

    public InstAndMaterialInfo() {
    }

    public InstAndMaterialInfo(int matchOrderId, List<String> materialNameList, List<Integer> materialQuantityList) {
        this.matchOrderId = matchOrderId;
        this.materialNameList = materialNameList;
        this.materialQuantityList = materialQuantityList;
    }
}
