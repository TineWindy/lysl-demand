package com.whu.lysl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.shiyingpu.yinghuaji.dao")
public class LyslApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyslApplication.class, args);
	}

}
