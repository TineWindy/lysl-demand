package com.whu.lysl.entity.condition;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 01:08
 * @Description:
 */
public class NewsCondition {

    private Integer id;

    private Date gmtCreated;

    private Date gmtModified;

    private Date publishDatetime;

    private String title;

    private String origin;

    private String url;

    public NewsCondition(Integer id, Date publishDatetime, String title, String origin, String url) {
        this.id = id;
        this.publishDatetime = publishDatetime;
        this.title = title;
        this.origin = origin;
        this.url = url;
    }

    public static final class Builder {
        private Integer id;

        private Date publishDatetime;

        private String title;

        private String origin;

        private String url;

        public NewsCondition.Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public NewsCondition.Builder publishDatetime(Date publishDatetime) {
            this.publishDatetime = publishDatetime;
            return this;
        }

        public NewsCondition.Builder title(String title) {
            this.title = title;
            return this;
        }

        public NewsCondition.Builder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public NewsCondition.Builder url(String url) {
            this.url = url;
            return this;
        }

    }
}
