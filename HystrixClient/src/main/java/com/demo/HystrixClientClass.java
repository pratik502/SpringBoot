package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/get")
@DefaultProperties(threadPoolKey="mainPool",threadPoolProperties= {
	@HystrixProperty(name = "coreSize",value="3"), @HystrixProperty(name="maxQueueSize", value="1")})
public class HystrixClientClass {

	@Autowired
	public RestTemplate restTemplate;

	
			
	@HystrixCommand(fallbackMethod="fallbackString",
	commandProperties= {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")},
	threadPoolKey="hystrixClientPool",threadPoolProperties= {
	@HystrixProperty(name = "coreSize",value="2"), @HystrixProperty(name="maxQueueSize", value="1")}
	)

	@GetMapping("/hclient")
	public String get() {
		String str=null;
		
		str=restTemplate.getForObject("http://HYSTRIX-SERVICE/hservice/get", String.class);
		str+=" "+Thread.currentThread().getName();
		
		return str;
	}
	
	@GetMapping("/hclient1")
	@HystrixCommand(fallbackMethod="fallbackString",
	commandProperties= {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")}
	)
	public String get1() {
		String str=null;
		
		str=restTemplate.getForObject("http://HYSTRIX-SERVICE/hservice/get1", String.class);
		str+=" "+Thread.currentThread().getName();
		
		return str;
	}

	public String fallbackString() {
		return "Fallback started";
	}
}
