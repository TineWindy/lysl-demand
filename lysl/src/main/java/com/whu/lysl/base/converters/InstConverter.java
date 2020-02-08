package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dto.Institution;

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
        institution.setCity(institutionDO.getCity());
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

}
