package com.movingapp;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@SpringBootApplication
@RestController
@ComponentScan(basePackages ="com.movingapp")
@EnableJpaRepositories("com.movingapp.dao")
@Import({ SecurityConfig.class })
public class UiApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
	
	@RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
}
