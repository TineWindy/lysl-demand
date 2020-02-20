package com.whu.lysl.entity.dbobj;


import lombok.Data;

import java.util.Date;

/**
 * 需求do
 * @author Powen
 */
@Data
public class DemandDO {

    private Integer id;

    private Date gmtCreated;

    private Date gmtModified;

    private String demandId;

    private int institutionId;

    private int doneeId;

    private int materialId;

    private String materialName;

    private int materialNum;

    private String status;

    private String description;

}
