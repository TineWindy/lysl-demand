package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 匹配单模型类
 * @author Jzh
 * @since 2020-02-09 17:45
 **/
@Data
public class MatchOrder {

    /**
     * 匹配单id
     */
    int id;
    /**
     * 匹配单创建时间
     */
    Date gmtCreated;
    /**
     * 匹配单修改时间
     */
    Date gmtModified;
    /**
     * 捐赠者id
     */
    int donorId;
    /**
     * 捐赠者的名字
     */
    String donorName;
    /**
     * 受赠者id
     */
    int doneeId;
    /**
     * 受赠者的名字
     */
    String doneeName;
    /**
     * 物资id列表，对应着一个匹配单
     */
    List<Integer> materialIdList;
    /**
     * 物资数量列表，对应着物资
     */
    List<Integer> materialQuantityList;
    /**
     * 匹配单状态
     */
    String status;
    /**
     * 匹配方式（包含三种：系统匹配、定向捐赠、志愿者人工匹配）
     */
    String matchingMethod;
    /**
     * 捐赠单id
     */
    int donationOrderId;
    /**
     * 需求单id
     */
    int demandOrderId;
    /**
     * 物流单号
     */
    String logisticCode;
    /**
     * 捐赠类型
     */
    String donationType;
    /**
     * 物资名字
     */
    List<String> materialNameList;
    /**
     * 快递公司缩写
     */
    String shipperCode;
    /**
     * 备注信息
     */
    String remark;
    /**
     * 捐赠者联系方式
     */
    String donorPhone;
    /**
     * 图片列表
     */
    String[] picList;

    public void setMaterial(List<MaterialOrder> materialOrderList){
        if(materialOrderList == null || materialOrderList.size() == 0){
            return;
        }
        this.materialNameList = new ArrayList<>();
        this.materialIdList = new ArrayList<>();
        this.materialQuantityList = new ArrayList<>();
        for (int i=0;i<materialOrderList.size();i++){
            MaterialOrder materialOrder = materialOrderList.get(i);
            materialIdList.add(materialOrder.getMaterialId());
            materialNameList.add(materialOrder.getMaterialName());
            materialQuantityList.add(materialOrder.getMaterialAmount());
        }
    }

    /**
     * 生成物资列表的str，用于发送短信
     */
    public String getMaterialStrList(){
        // 生成物资列表
        String materials = "";
        if(materialNameList.size() == 1){
            materials = materialNameList.get(0);
        }
        else{
            materials = "[";
            for(int j = 0;j<materialNameList.size();j++){
                materials += materialNameList.get(j) + ",";
            }
            materials = materials.substring(0,materials.length()-1);
            materials += "]";
        }
        return materials;
    }
}
