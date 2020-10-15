package com.xinyue.cloudboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@NacosPropertySource(dataId = "cloud-boot-client",autoRefreshed=true, groupId="cloudboot")
public class ClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	
	@LoadBalanced  //开启负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
