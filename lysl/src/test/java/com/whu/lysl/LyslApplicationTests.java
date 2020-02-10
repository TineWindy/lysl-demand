package com.whu.lysl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whu.lysl.entity.dto.User;
import com.whu.lysl.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
@EnableTransactionManagement
@EnableScheduling
@RunWith(SpringRunner.class)
@MapperScan("com.whu.lysl.dao")
public class LyslApplicationTests {

    @Resource
    private UserService userService;

	@Test
	public void contextLoads() {
        Page page= PageHelper.startPage(2, 2);
        List<User> users = userService.getAllUser();
        System.out.println(users);
	}

}
