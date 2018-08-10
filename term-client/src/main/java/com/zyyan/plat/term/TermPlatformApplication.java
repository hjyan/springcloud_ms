package com.zyyan.plat.term;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hongdian.plat"})
public class TermPlatformApplication{

	public static void main(String[] args) {
		SpringApplication.run(TermPlatformApplication.class, args);
	}

}
