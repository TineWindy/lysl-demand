package com.whu.lysl.service.demand.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.converters.DemandConverter;
import com.whu.lysl.base.converters.InstConverter;
import com.whu.lysl.base.converters.UserConverter;
import com.whu.lysl.dao.DemandDAO;
import com.whu.lysl.dao.InstitutionDAO;
import com.whu.lysl.dao.UserDAO;
import com.whu.lysl.entity.dbobj.DemandDO;
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
    @Transactional(rollbackFor = Exception.class)
    public List<DemandVO> getUnreviewedDemands() {
        List<DemandDO> demandDOList = demandDAO.showUnreviewedDemands();
        return DemandConverter.installVO(demandDOList, institutionService, userService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DemandVO> getUnreviewedDemandsById(String jsonString) {
        List<DemandDO> demandDOList = demandDAO.showUnreviewedDemandsById(
                (int) JSON.parseObject(jsonString).get("institutionId"));
        return DemandConverter.installVO(demandDOList, institutionService, userService);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertDemand(String jsonString){
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONObject jsonInstitution = jsonObject.getJSONObject("institution");
        JSONObject jsonUser = jsonObject.getJSONObject("donee");
        Institution institution = jsonInstitution.toJavaObject(Institution.class);
        User user = jsonUser.toJavaObject(User.class);
        JSONArray materials = (JSONArray) jsonObject.get("materials");
        String description = jsonObject.getString("description");
        int institutionId = institutionService.addAnInstitution(institution);
        user.setInstitutionId(institutionId);
        int userId = userService.addAnUser(user);
        List<DemandDO> demandDOList = DemandConverter.json2DO(institutionId, userId, description, materials);
        for(DemandDO demandDO : demandDOList)
            demandDAO.insertDemand(demandDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyStatus(String jsonString){
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String demandId = jsonObject.getString("demandId");
        String status = jsonObject.getString("status");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        demandDAO.modifyStatus(demandId, status, df.format(new Date()));
    }
}
