package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 01:01
 * @Description: 新闻类
 */
@Data
public class News {

    private Integer id;

    private Date gmtCreated;

    private Date gmtModified;

    private Date publishDatetime;

    private String title;

    private String origin;

    private String url;



}
