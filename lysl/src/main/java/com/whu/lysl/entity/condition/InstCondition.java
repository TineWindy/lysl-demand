package com.whu.lysl.entity.condition;

import lombok.Data;

/**
 * 机构查询
 * @author Visionary
 * @since 2020/2/8 9:43 PM
 */
@Data
public class InstCondition {

    private Integer id;

    private String name;

    private String status;

    private String province;

    private String city;

    private String district;

    public InstCondition(Integer id, String name, String status, String province, String city, String district) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public InstCondition() {
    }

    public static final class Builder {
        private Integer id;

        private String name;

        private String status;

        private String province;

        private String city;

        private String district;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder district(String district) {
            this.district = district;
            return this;
        }

        public InstCondition build() {
            return new InstCondition(this.id, this.name, this.status
            , this.province, this.city, this.district);
        }
    }

}
