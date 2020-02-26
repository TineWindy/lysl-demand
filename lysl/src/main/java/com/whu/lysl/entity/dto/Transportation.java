package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 13:24
 * @Description: 物流公式信息
 */
@Data
public class Transportation {

    private Integer id;

    private Date gmtCreated;

    private Date gmtModified;

    private String name;

    private String linkMan;

    private String linkMobile;

    private String deliveryScope;

    private Integer deleted;

    private String remark;

    private String checkStatus;


}
