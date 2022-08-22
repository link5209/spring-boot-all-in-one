package com.springboot.allinone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.springboot.allinone.mapper")
public class SpringBootAllInOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAllInOneApplication.class, args);
	}

}
