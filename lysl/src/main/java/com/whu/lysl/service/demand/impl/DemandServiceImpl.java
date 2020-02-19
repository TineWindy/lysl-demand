package com.whu.lysl.service.demand.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.converters.DemandConverter;
import com.whu.lysl.base.converters.InstConverter;
import com.whu.lysl.base.converters.UserConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.OrderStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.dao.DemandDAO;
import com.whu.lysl.dao.InstitutionDAO;
import com.whu.lysl.dao.UserDAO;
import com.whu.lysl.entity.condition.DemandCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dto.Demand;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.User;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.service.demand.DemandService;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 机构服务实现
 * @author Visionary
 * @since 2020/2/8 9:54 PM
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Resource
    private DemandDAO demandDAO;

    @Resource
    private InstitutionService institutionService;

    @Resource
    private UserService userService;

    @Override
    public List<DemandVO> getUnreviewedDemands() {
        List<DemandDO> demandDOList = demandDAO.showUnreviewedDemands();
        return DemandConverter.installVO(demandDOList, institutionService, userService);
    }

    @Override
    public List<DemandVO> getUnreviewedDemandsById(String jsonString) {
        List<DemandDO> demandDOList = demandDAO.showUnreviewedDemandsById(
                (int) JSON.parseObject(jsonString).get("institutionId"));
        return DemandConverter.installVO(demandDOList, institutionService, userService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDemand(Institution institution, User user, List<Map<String, String>> materials, String description) {
        int insiId = institutionService.addAnInstitution(institution);
        int userId = userService.addAnUser(user);

        int demandId = 0;
        for (Map<String, String> map : materials) {
            Demand demand = new Demand();
            demand.setDemandId("");
            demand.setDoneeId(userId);
            demand.setInstitutionId(insiId);
            demand.setMaterialId(Integer.parseInt(map.get("materialId")));
            demand.setMaterialNum(Integer.parseInt(map.get("materialNum")));
            demand.setMaterialName(map.get("materialName"));
            demand.setStatus(OrderStatusEnum.UNCHECKED.getCode());

            DemandDO demandDO = DemandConverter.model2Do(demand);
            if (demandId == 0) {
                demandDAO.insertDemand(demandDO);
                demandId = demandDO.getId();
                demandDO.setDemandId(demandId + "");
                demandDAO.update(demandDO);
            } else {
                demandDO.setDemandId(demandId + "");
                demandDAO.insertDemand(demandDO);

            }
        }

        return demandId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyDemandOrderStatus(String demandId, OrderStatusEnum orderStatusEnum) {
        // 1. 检验需求单状态合法性
        AssertUtils.StringNotEmpty(demandId, "demandId cant be empty when verify a demand!");
        List<DemandDO> demandDOS = demandDAO.selectByDemandIdForUpdate(demandId);
        if (demandDOS != null && demandDOS.size() > 0) {
            if (!StringUtils.equal(demandDOS.get(0).getStatus(), OrderStatusEnum.UNCHECKED.getCode())) {
                throw new LYSLException("status of demands must be unchecked when veriry", LYSLResultCodeEnum.DATA_INVALID);
            }
        }

        // 2.
        demandDAO.modifyStatus(demandId, orderStatusEnum.getCode());
    }

    // 这里使用 DO 是兼顾旧写法
    // 实际上在 service 层应该使用 dto
    @Override
    public List<DemandDO> getDemandsByCondition(DemandCondition demandCondition) {
        AssertUtils.AssertNotNull(demandCondition, "查询需求时条件不能为空");

        return demandDAO.selectByCondition(demandCondition);
    }
}
