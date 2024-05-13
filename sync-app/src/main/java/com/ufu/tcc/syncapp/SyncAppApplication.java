package com.ufu.tcc.syncapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
				"com.ufu.tcc",
				"com.ufu.tcc.syncapp" ,
				"com.ufu.tcc.commonsdomain",
				"com.ufu.tcc.commonsdomain.repository",
		})
@EnableJpaRepositories(basePackages = {"com.ufu.tcc.*"})
@EntityScan(basePackages = {"com.ufu.tcc.*"})
public class SyncAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncAppApplication.class, args);
	}

}
