package com.assignment.map.herecustom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching(proxyTargetClass = true)
public class HerecustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerecustomApplication.class, args);
	}

}
