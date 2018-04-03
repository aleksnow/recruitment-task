package com.pandziarze.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class TaskApp {
	
	public static void main(String[] args) {
		SpringApplication.run(TaskApp.class, args);
	}

}
