package com.movingapp;

import java.security.Principal;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@ComponentScan(basePackages ="com.movingapp")
@EnableJpaRepositories("com.movingapp.dao")
@Import({ SecurityConfig.class })
public class UiApplication {

	//this adds http too
//	@Bean
//	public Integer port() {
//		return SocketUtils.findAvailableTcpPort();
//	}
//
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
//		return tomcat;
//	}
//
//	private Connector createStandardConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setPort(port());
//		return connector;
//	}
    
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
	
	@RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
}
