package io.java.spring.microservices.musicchartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MusicChartServiceApplication {
	
	@Bean
	public WebClient.Builder getWebClientBuilder(LoadBalancerClient loadBalancerClient) {
		return WebClient.builder()
        .filter(new LoadBalancerExchangeFilterFunction(loadBalancerClient));
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicChartServiceApplication.class, args);
	}

}
