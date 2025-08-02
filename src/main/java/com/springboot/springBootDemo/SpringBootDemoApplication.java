package com.springboot.springBootDemo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//Admin@74
@SpringBootApplication
@EnableJpaRepositories("com.springboot.springBootDemo.repository")
@EntityScan("com.springboot.springBootDemo.model")
@EnableJpaAuditing(auditorAwareRef = "auditImpl")
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}