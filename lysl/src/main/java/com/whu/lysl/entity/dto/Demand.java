package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 需求模型类
 * @author Powen
 */
@Data
public class Demand {

    private Integer id;

    private String gmtCreated;

    private String demandId;

    private int institutionId;

    private int doneeId;

    private String material;

    private int materialNum;

    private String address;

    private String status;

    private String description;

}
