package com.xinyue.cloudboot.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerImpl {
	
	private final Logger logger = LoggerFactory.getLogger(ServerImpl.class);
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/add" ,method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a, @RequestParam Integer b){
	 Integer r = a + b;
	 logger.info("/add, host:" + client.description());
	 return r;
    }
}
