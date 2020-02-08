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

    public InstCondition(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public static final class Builder {
        private Integer id;

        private String name;

        private String status;

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

        public InstCondition build() {
            return new InstCondition(this.id, this.name, this.status);
        }
    }

}
