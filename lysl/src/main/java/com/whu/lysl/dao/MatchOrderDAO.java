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

    List<MatchOrderDo> selectByDonorIdAndDonationOrderId(int donorId,int donationOrderId);
}
