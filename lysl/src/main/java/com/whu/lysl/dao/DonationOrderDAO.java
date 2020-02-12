package com.whu.lysl.dao;

import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.condition.MaterialOrderCondition;
import com.whu.lysl.entity.dbobj.DonationOrderDO;
import com.whu.lysl.entity.dbobj.MaterialOrderDO;
import com.whu.lysl.entity.dto.DonationOrder;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @Author Caspar
 * @Date 2020/2/9 15:09
 * @Description:
 */
@Mapper
public interface DonationOrderDAO{

    /**
     * 根据条件查询机构
     * @param donationOrderCondition 查询条件
     * @return list
     */
    List<DonationOrderDO> selectByCondition(DonationOrderCondition donationOrderCondition);

    int insertDonationOrder(DonationOrderDO donationOrder);

    int updateDonationOrder(DonationOrderDO donationOrder);

    int insertMaterialOrder(MaterialOrderDO materialOrder);

    List<MaterialOrderDO> selectMaterialOrderByCondition(MaterialOrderCondition donationOrderCondition);

}