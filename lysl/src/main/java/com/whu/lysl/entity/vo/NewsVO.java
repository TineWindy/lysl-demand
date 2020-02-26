package com.whu.lysl.entity.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 01:26
 * @Description:
 */

@Data
public class NewsVO {

    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDatetime;

    private String title;

    private String origin;

    private String url;

}
