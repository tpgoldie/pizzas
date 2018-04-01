package com.tpg.pjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class PjsApplication {

	public static void main(String[] args) {

		SpringApplication.run(PjsApplication.class, args);
	}
}
