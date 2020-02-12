package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 需求模型类
 * @author Powen
 */
@Data
public class Demand {

    private String gmtCreated;

    private String gmtModified;

    private String demandId;

    private int institutionId;

    private int doneeId;

    private String materialName;

    private int materialId;

    private int materialNum;

    private String status;

    private String description;

}
