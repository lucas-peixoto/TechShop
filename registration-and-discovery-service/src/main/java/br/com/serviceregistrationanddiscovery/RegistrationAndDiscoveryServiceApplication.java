package br.com.serviceregistrationanddiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistrationAndDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationAndDiscoveryServiceApplication.class, args);
	}
}
