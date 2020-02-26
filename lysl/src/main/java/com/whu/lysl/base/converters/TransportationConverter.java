package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.TransportationDO;
import com.whu.lysl.entity.dto.Transportation;
import com.whu.lysl.entity.vo.TransportationVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Caspar
 * @Date 2020/2/26 01:21
 * @Description:
 */
public class TransportationConverter {

    /**
     * do 2 model
     * @param transportationDO do
     * @return model
     */
    public static Transportation do2Model(TransportationDO transportationDO) {
        if (transportationDO == null) {
            return null;
        }

        Transportation transportation = new Transportation();
        transportation.setId(transportationDO.getId());
        transportation.setGmtCreated(transportationDO.getGmtCreated());
        transportation.setGmtModified(transportationDO.getGmtModified());
        transportation.setName(transportationDO.getName());
        transportation.setLinkMan(transportationDO.getLinkMan());
        transportation.setLinkMobile(transportationDO.getLinkMobile());
        transportation.setDeliveryScope(transportationDO.getDeliveryScope());
        transportation.setDeleted(transportationDO.getDeleted());
        transportation.setRemark(transportationDO.getRemark());
        transportation.setCheckStatus(transportationDO.getCheckStatus());

        return transportation;
    }

    /**
     * 批量 do 2 model
     * @param transportationDOS do list
     * @return model list
     */
    public static List<Transportation> batchDo2Model(List<TransportationDO> transportationDOS) {
        List<Transportation> transportations = new ArrayList<>();

        if (transportationDOS == null) {
            return transportations;
        }

        for (TransportationDO transportationDO : transportationDOS) {
            transportations.add(TransportationConverter.do2Model(transportationDO));
        }
        return transportations;
    }

    /**
     * model 2 do
     * @param transportation model
     * @return do
     */
    public static TransportationDO model2DO(Transportation transportation) {
        if (transportation == null) {
            return null;
        }

        TransportationDO transportationDO = new TransportationDO();
        transportationDO.setId(transportation.getId());
        transportationDO.setGmtCreated(transportation.getGmtCreated());
        transportationDO.setGmtModified(transportation.getGmtModified());
        transportationDO.setName(transportation.getName());
        transportationDO.setLinkMan(transportation.getLinkMan());
        transportationDO.setLinkMobile(transportation.getLinkMobile());
        transportationDO.setDeliveryScope(transportation.getDeliveryScope());
        transportationDO.setDeleted(transportation.getDeleted());
        transportationDO.setRemark(transportation.getRemark());
        transportationDO.setCheckStatus(transportation.getCheckStatus());

        return transportationDO;
    }

    /**
     * vo 2 model
     * @param transportationVO vo
     * @return model
     */
    public static Transportation vo2Model(TransportationVO transportationVO) {
        if (transportationVO == null) {
            return null;
        }

        Transportation transportation = new Transportation();
        transportation.setId(transportationVO.getId());
        transportation.setName(transportationVO.getName());
        transportation.setLinkMan(transportationVO.getLinkMan());
        transportation.setLinkMobile(transportationVO.getLinkMobile());
        transportation.setDeliveryScope(transportationVO.getDeliveryScope());
        transportation.setRemark(transportationVO.getRemark());

        return transportation;
    }

    /**
     * model 2 vo
     * @param transportation model
     * @return vo
     */
    public static TransportationVO model2VO(Transportation transportation) {
        if (transportation == null) {
            return null;
        }

        TransportationVO transportationVO = new TransportationVO();
        transportationVO.setId(transportation.getId());
        transportationVO.setName(transportation.getName());
        transportationVO.setLinkMan(transportation.getLinkMan());
        transportationVO.setLinkMobile(transportation.getLinkMobile());
        transportationVO.setDeliveryScope(transportation.getDeliveryScope());
        transportationVO.setRemark(transportation.getRemark());

        return transportationVO;
    }

    /**
     * 批量 model 2 vo
     * @param listTransportation model list
     * @return vo list
     */
    public static List<TransportationVO> batchModel2VO(List<Transportation> listTransportation) {
        List<TransportationVO> transportationVOS = new ArrayList<>();

        if (listTransportation == null) {
            return transportationVOS;
        }

        for (Transportation transportation : listTransportation) {
            transportationVOS.add(TransportationConverter.model2VO(transportation));
        }
        return transportationVOS;
    }

}
