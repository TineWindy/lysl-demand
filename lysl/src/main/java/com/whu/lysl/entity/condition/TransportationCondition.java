package com.whu.lysl.entity.condition;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 13:41
 * @Description:
 */
public class TransportationCondition {

    private Integer id;

    private String name;

    private String linkMan;

    private String linkMobile;

    private String deliveryScope;

    private Integer deleted;

    private String remark;

    private String checkStatus;

    public TransportationCondition(Integer id, String name, String linkMan, String linkMobile
            , String deliveryScope, Integer deleted, String remark, String checkStatus) {
        this.id = id;
        this.name = name;
        this.linkMan = linkMan;
        this.linkMobile = linkMobile;
        this.deliveryScope = deliveryScope;
        this.deleted = deleted;
        this.remark = remark;
        this.checkStatus = checkStatus;

    }

    public TransportationCondition() {
    }

    public static final class Builder {

        private Integer id;

        private String name;

        private String linkMan;

        private String linkMobile;

        private String deliveryScope;

        private Integer deleted;

        private String remark;

        private String checkStatus;

        public TransportationCondition.Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public TransportationCondition.Builder name(String name) {
            this.name = name;
            return this;
        }

        public TransportationCondition.Builder linkMan(String linkMan) {
            this.linkMan = linkMan;
            return this;
        }

        public TransportationCondition.Builder linkMobile(String linkMobile) {
            this.linkMobile = linkMobile;
            return this;
        }

        public TransportationCondition.Builder deliveryScope(String deliveryScope) {
            this.deliveryScope = deliveryScope;
            return this;
        }

        public TransportationCondition.Builder deleted(Integer deleted) {
            this.deleted = deleted;
            return this;
        }

        public TransportationCondition.Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public TransportationCondition.Builder checkStatus(String checkStatus) {
            this.checkStatus = checkStatus;
            return this;
        }

        public TransportationCondition build() {
            return new TransportationCondition(this.id, this.name, this.linkMan
                    , this.linkMobile, this.deliveryScope, this.deleted, this.remark, this.checkStatus);
        }
    }

}
