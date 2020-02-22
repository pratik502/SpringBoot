package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/get")
public class HystrixClientClass {

	@Autowired
	public RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod="fallbackString",
	threadPoolKey="hystrixClientPool",threadPoolProperties= {
	@HystrixProperty(name = "coreSize",value="2"), @HystrixProperty(name="maxQueueSize", value="0"),
	},commandProperties= {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")}
	)

	@GetMapping("/hclient")
	public String get() {
		String str=null;
		//return restTemplate.getForObject("http://HYSTRIX-SERVICE/hservice/get", String.class);
		
			 str=restTemplate.getForObject("http://HYSTRIX-SERVICE/hservice/get", String.class);
			 str+=" "+Thread.currentThread().getName();
		
		return str;
	}

	public String fallbackString() {
		return "Fallback started";
	}
}
