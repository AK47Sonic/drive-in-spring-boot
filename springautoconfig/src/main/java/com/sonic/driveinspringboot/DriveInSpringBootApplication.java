package com.sonic.driveinspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ServletComponentScan(basePackages = "com.sonic.driveinspringboot.web.servlet")
public class DriveInSpringBootApplication {

	/**
	*
	* @author Sonic
	* @date 2018/11/29 11:31
	* @param [args]
	* @return void
	*/
	public static void main(String[] args) {
//		new SpringApplicationBuilder(DriveInSpringBootApplication.class).web(WebApplicationType.NONE).run(args);
		SpringApplication.run(DriveInSpringBootApplication.class, args);
	}
}
