package com.whu.lysl.service.institution.impl;

import com.whu.lysl.base.converters.InstConverter;
import com.whu.lysl.dao.InstitutionDAO;
import com.whu.lysl.entity.condition.InstCondition;
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
}
