package com.example.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class DsApplication {
	public static void main(String[] args) {
	    SpringApplication.run(DsApplication.class, args);
	  }

}
