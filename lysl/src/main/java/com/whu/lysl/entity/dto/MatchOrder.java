package com.whu.lysl.entity.dto;

import lombok.Data;

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
     * 受赠者id
     */
    int doneeId;
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
    String trackingNumber;

}
