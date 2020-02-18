package com.whu.lysl;

import com.whu.lysl.base.enums.LYSLMessageEnum;
import com.whu.lysl.service.notice.NoticeService;
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
	private NoticeService noticeService;

	@Test
	public void contextLoads() {

//		noticeService.sendSingleMessage(LYSLMessageEnum.DONOR_SHIP, "18331131879", "jzh", "武汉大学", "口罩若干", "www.baidu.com", "15282329012");

	}

}
