package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cloud.CloudServiceConnectorsAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


//@EnableDiscoveryClient
//@EnableFeignClients
@SpringBootApplication
public class SpringDiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDiscoveryClientApplication.class, args);
	}

}
