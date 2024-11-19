package com.calistenia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CalisthenicsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalisthenicsAppApplication.class, args);
	}

}
