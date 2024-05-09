package com.ufu.tcc.syncapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ufu.tcc", "com.ufu.tcc.commonsdomain.*"})
//@EntityScan(basePackages = {"com.ufu.tcc", "com.ufu.tcc.commonsdomain.*"})
public class SyncAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncAppApplication.class, args);
	}

}
