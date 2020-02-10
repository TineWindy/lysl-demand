package com.whu.lysl;

import com.whu.lysl.base.utils.DateUtils;
import com.whu.lysl.dao.DemandDAO;
import com.whu.lysl.entity.dbobj.DemandDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@EnableTransactionManagement
@EnableScheduling
@RunWith(SpringRunner.class)
public class DemandTest {

    @Resource
    private DemandDAO demandDAO;

    @Test
    public void insert() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DemandDO demandDO = new DemandDO();
        demandDO.setId(0);
        demandDO.setGmtCreated(df.format(new Date()));
        demandDO.setDemandId("asd");
        demandDO.setInstitutionId(1);
        demandDO.setDoneeId(117);
        demandDO.setMaterial("卫生纸");
        demandDO.setMaterialNum(20000);
        demandDO.setAddress("湖北省武汉市协和医院");
        demandDO.setStatus("未审核");
        demandDO.setDescription("无");

        demandDAO.insertDemand(demandDO);
    }
}