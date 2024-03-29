package io.java.spring.microservices.musictrackdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MusicTrackDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicTrackDetailsServiceApplication.class, args);
	}

}
