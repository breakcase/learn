package com.recive.sbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbusApp {

	private static final Logger logger = LoggerFactory.getLogger(SbusApp.class);

	public static void main(String[] args) {
		SpringApplication.run(SbusApp.class, args);
		logger.info("===============SBUS系统启动成功!==================");
	}

}
