package io.java.spring.microservices.chartpositiondataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChartPositionDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChartPositionDataServiceApplication.class, args);
	}
	
}
