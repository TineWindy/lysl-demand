package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * 匹配单DO
 * @author Jzh
 * @since 2020-02-09 16:58
 **/

@Data
public class MatchOrderDo implements Cloneable{
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
     * 捐赠者名字
     */
    String donorName;
    /**
     * 受赠者名字
     */
    String doneeName;
    /**
     * 物资名字
     */
    String materialName;
    /**
     * 受赠者id
     */
    int doneeId;
    /**
     * 物资id
     */
    int materialId;
    /**
     * 物资数量
     */
    int materialQuantity;
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
    String trackingNumber;

    @Override
    public MatchOrderDo clone() throws CloneNotSupportedException {
        return (MatchOrderDo) super.clone();
    }
}
