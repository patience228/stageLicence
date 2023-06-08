package com.id2real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@EntityScan(basePackages = {"com.id2real"})
public class GeviscoStartUp extends SpringBootServletInitializer  {

	public static final Logger LOG = LoggerFactory.getLogger(GeviscoStartUp.class);

	/**
	* @param args the command line arguments
	*/
	public static void main(String[] args) {
	SpringApplication.run(GeviscoStartUp.class, args);
	}
}
