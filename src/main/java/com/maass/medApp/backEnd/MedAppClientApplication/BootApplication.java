package com.maass.medApp.backEnd.MedAppClientApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan({"com.maass.medApp.backEnd"})
@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableMongoRepositories({"com.maass.medApp.backEnd.Repositories"})
public class BootApplication {
	
	@RequestMapping("/")
	public String hello() {
		return "*********************************Medical Back End For Front Office Application is UP and RUNNING*********************************";
	}
	
	public static void main (String args[]){
		SpringApplication.run(BootApplication.class, args);
	}
	
}