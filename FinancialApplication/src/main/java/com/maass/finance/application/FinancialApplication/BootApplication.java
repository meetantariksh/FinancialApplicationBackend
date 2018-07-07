package com.maass.finance.application.FinancialApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maass.finance.application.FinancialApplication.BootApplication;

@ComponentScan({"com.maass.finance.application.controller"})
@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableMongoRepositories({"com.maass.finance.application.repositories"})
public class BootApplication {
	
	@RequestMapping("/")
	public String hello() {
		return "*********************************Financial Back End Application is UP and RUNNING*********************************";
	}
	
	public static void main (String args[]){
		SpringApplication.run(BootApplication.class, args);
	}
	
}
