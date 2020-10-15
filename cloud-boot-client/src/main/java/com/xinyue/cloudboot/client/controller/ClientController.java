package com.xinyue.cloudboot.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xinyue.cloudboot.client.interfaces.ClientService;

@RestController
public class ClientController {
	
	@Autowired 
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/getServiceList") 
	public List<ServiceInstance> getServiceList() {
		List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("cloud-boot-client"); 
		return serviceInstanceList;
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String add(){
		return restTemplate.getForEntity("http://cloud-boot-server/add?a=10&b=20", String.class).getBody();
	}
	
	public String addServiceFallback(){
		return"error";
	}
	
	@Autowired
	ClientService clientService;
	@RequestMapping(value = "/add2", method = RequestMethod.GET)
	public Integer add2(){
		return clientService.add(10, 20);
	}
	
	
}
