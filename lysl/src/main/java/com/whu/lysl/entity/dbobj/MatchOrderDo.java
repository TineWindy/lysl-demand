package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * 匹配单DO
 * @author Jzh
 * @since 2020-02-09 16:58
 **/

@Data
public class MatchOrderDo {
    /**
     * 匹配单id
     */
    int id;
    /**
     * 匹配单创建时间
     */
    Date gmt_created;
    /**
     * 匹配单修改时间
     */
    Date gmt_modified;
    /**
     * 捐赠者id
     */
    int donor_id;
    /**
     * 受赠者id
     */
    int donee_id;
    /**
     * 物资id
     */
    int material_id;
    /**
     * 物资数量
     */
    int material_quantity;
    /**
     * 匹配单状态
     */
    String status;
    /**
     * 匹配方式（包含三种：系统匹配、定向捐赠、志愿者人工匹配）
     */
    String matching_method;
    /**
     * 捐赠单id
     */
    int donation_order_id;
    /**
     * 需求单id
     */
    int demand_order_id;
    /**
     * 物流单号
     */
    String tracking_number;

}
