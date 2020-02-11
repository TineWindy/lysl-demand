package com.whu.lysl.service.institution.impl;

import com.whu.lysl.base.converters.InstConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.dao.InstitutionDAO;
import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.service.institution.InstitutionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机构服务实现
 * @author Visionary
 * @since 2020/2/8 9:54 PM
 */
@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Resource
    private InstitutionDAO institutionDAO;

    @Override
    public List<Institution> getInstsByCondition(InstCondition instCondition) {
        return InstConverter.batchDo2Model(institutionDAO.selectByCondition(instCondition));
    }

    @Override
    public List<Institution> getInstsByPartitionOfName(String name) {
        return InstConverter.batchDo2Model(institutionDAO.queryByPartitionOfName(name));
    }

    @Override
    public int addAnInstitution(Institution institution) {
        checkInstitution(institution);
        InstitutionDO institutionDO = InstConverter.model2Do(institution);
        institutionDAO.insert(institutionDO);
        return institutionDO.getId();
    }

    /**
     * 检验录入的机构信息
     * @param institution 机构信息
     */
    private void checkInstitution(Institution institution) {
        AssertUtils.AssertNotNull(institution);

        try {
            AssertUtils.StringNotEmpty(institution.getName());
            AssertUtils.StringNotEmpty(institution.getProvince());
            AssertUtils.StringNotEmpty(institution.getCity());
            AssertUtils.StringNotEmpty(institution.getAddress());
            AssertUtils.StringNotEmpty(institution.getAuth());
        } catch (Exception e) {
            throw new LYSLException("录入机构信息不足", LYSLResultCodeEnum.DATA_INVALID);
        }
    }

    @Override
    public void checkInstitutionStatus(int institutionId, boolean isPassed) {

    }
}
