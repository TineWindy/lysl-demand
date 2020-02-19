package com.whu.lysl;

import com.whu.lysl.service.system.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;


@SpringBootTest
@EnableTransactionManagement
@EnableScheduling
@RunWith(SpringRunner.class)
@MapperScan("com.whu.lysl.dao")
public class LyslApplicationTests {

    @Resource
    private SystemService systemService;

    @Test
    public void contextLoads() {
    }
}
