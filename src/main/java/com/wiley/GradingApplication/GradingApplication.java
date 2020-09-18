package com.wiley.GradingApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan({"com.wiley.GradingApplication"})
public class GradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradingApplication.class, args);
	}

}
