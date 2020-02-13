package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.vo.InstitutionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构类型转换
 * @author Visionary
 * @since 2020/2/8 8:40 PM
 */
public class InstConverter {

    /**
     * do 2 model
     * @param institutionDO do
     * @return model
     */
    public static Institution do2Model(InstitutionDO institutionDO) {
        if (institutionDO == null) {
            return null;
        }

        Institution institution = new Institution();
        institution.setId(institutionDO.getId());
        institution.setGmtCreated(institutionDO.getGmtCreated());
        institution.setGmtModified(institutionDO.getGmtModified());
        institution.setName(institutionDO.getName());
        institution.setType(institutionDO.getType());
        institution.setCity(institutionDO.getCity());
        institution.setDistrict(institutionDO.getDistrict());
        institution.setAddress(institutionDO.getAddress());
        institution.setStatus(institutionDO.getStatus());
        institution.setAuth(institutionDO.getAuth());
        return institution;
    }

    /**
     * batch do 2 model
     * @param institutionDOS do list
     * @return model list
     */
    public static List<Institution> batchDo2Model(List<InstitutionDO> institutionDOS) {
        List<Institution> institutions = new ArrayList<>();

        if(institutionDOS == null) {
            return institutions;
        }

        for (InstitutionDO institutionDO: institutionDOS) {
            institutions.add(do2Model(institutionDO));
        }

        return institutions;
    }

    /**
     * model 2 vo
     * @param institution model
     * @return vo
     */
    public static InstitutionVO model2VO(Institution institution) {
        if (institution == null) {
            return null;
        }

        InstitutionVO institutionVO = new InstitutionVO();

        institutionVO.setId(institution.getId());
        institutionVO.setGmtCreated(institution.getGmtCreated());
        institutionVO.setGmtModified(institution.getGmtModified());
        institutionVO.setName(institution.getName());
        institutionVO.setType(institution.getType());
        institutionVO.setCity(institution.getCity());
        institutionVO.setDistrict(institution.getDistrict());
        institutionVO.setAddress(institution.getAddress());
        institutionVO.setStatus(institution.getStatus());
        institutionVO.setAuth(institution.getAuth());

        return institutionVO;
    }

    /**
     * batch models 2 vos
     * @param institutionS models
     * @return vos
     */
    public static List<InstitutionVO> batchModel2VO(List<Institution> institutionS) {
        List<InstitutionVO> institutionVOS = new ArrayList<>();

        if (institutionS != null) {
            for (Institution institution: institutionS) {
                institutionVOS.add(model2VO(institution));
            }
        }

        return institutionVOS;
    }

    /**
     * vo 2 model
     * @param institutionVO vo
     * @return model
     */
    public static Institution vo2Model(InstitutionVO institutionVO) {
        if (institutionVO == null) {
            return null;
        }

        Institution institution = new Institution();
        institution.setId(institutionVO.getId());
        institution.setGmtCreated(institutionVO.getGmtCreated());
        institution.setGmtModified(institutionVO.getGmtModified());
        institution.setName(institutionVO.getName());
        institution.setType(institutionVO.getType());
        institution.setCity(institutionVO.getCity());
        institution.setDistrict(institutionVO.getDistrict());
        institution.setAddress(institutionVO.getAddress());
        institution.setStatus(institutionVO.getStatus());
        institution.setAuth(institutionVO.getAuth());
        return institution;
    }

    public static InstitutionDO model2Do(Institution institution) {
        if (institution == null) {
            return null;
        }

        InstitutionDO institutionDO = new InstitutionDO();

        institutionDO.setId(institution.getId());
        institutionDO.setGmtCreated(institution.getGmtCreated());
        institutionDO.setGmtModified(institution.getGmtModified());
        institutionDO.setName(institution.getName());
        institutionDO.setCity(institution.getCity());
        institutionDO.setProvince(institution.getProvince());
        institutionDO.setType(institution.getType());
        institutionDO.setDistrict(institution.getDistrict());
        institutionDO.setAddress(institution.getAddress());
        institutionDO.setStatus(institution.getStatus());
        institutionDO.setAuth(institution.getAuth());

        return institutionDO;
    }

}
