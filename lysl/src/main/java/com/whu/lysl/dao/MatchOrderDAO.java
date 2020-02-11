package com.whu.lysl.dao;

import com.whu.lysl.entity.dbobj.MatchOrderDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 匹配单 dao
 * @author Jzh
 * @since 2020-02-09 19:54
 **/
@Mapper
public interface MatchOrderDAO {

    /**
     * 对匹配单进行保存
     * @param matchOrderDo
     */
    void saveMatchOrder(MatchOrderDo matchOrderDo);

    /**
     * 根据donorId查匹配单详情
     * @param donorId
     * @return
     */
    List<Integer> selectDonationOrderIdByDonorId(int donorId);

    /**
     * 根据捐赠者id和捐赠单id查询匹配单
     * @param donorId
     * @param donationOrderId
     * @return
     */
    List<MatchOrderDo> selectByDonorIdAndDonationOrderId(int donorId,int donationOrderId);

    /**
     * 根据doneeId查匹配单详情
     * @param doneeId
     * @return
     */
    List<Integer> selectDemandOrderIdByDoneeId(int doneeId);

    /**
     * 根据受赠者id和捐赠单id查询匹配单
     * @param doneeId
     * @param demandOrderId
     * @return
     */
    List<MatchOrderDo> selectByDoneeIdAndDonationOrderId(int doneeId,int demandOrderId);

    /**
     * 更新匹配单状态
     * @param matchOrderId
     * @param status
     */
    void updateStatus(int matchOrderId,String status);

    /**
     * 更新物流单号
     * @param matchOrderId
     * @param trackingNumber
     */
    void updateTrackingNumber(int matchOrderId,String trackingNumber);
}
