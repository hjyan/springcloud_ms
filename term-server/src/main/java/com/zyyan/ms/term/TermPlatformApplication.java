package com.zyyan.ms.term;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zyyan.ms", "com.zyyan.ms.common"})
@RestController
//@EnableDiscoveryClient
public class TermPlatformApplication {

//	@Resource
//	AuthUtils authUtils;
	public static void main(String[] args) {
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(TermPlatformApplication.class);
//		builder.bannerMode(Banner.Mode.OFF).run(args);
		SpringApplication.run(TermPlatformApplication.class, args);
	}

//	@Override
//	public void run(String... strings) throws Exception {
//		System.out.println("权限校验结果：" + authUtils.checkPermission("wp:device:edit", "admin"));
//		System.out.println("角色校验结果：" + authUtils.checkRole("0", "admin"));
//	}
}
