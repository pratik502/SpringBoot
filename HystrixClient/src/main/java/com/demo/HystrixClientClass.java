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

	
	//This method will NOT use class level i.e Main thread pool as we have defined method level thread pool properties
	//You can observe thread number on UI, You will always see 2 threads as coresize is 2
	//If HYSTRIX-SERVICE which is another Spring project /Service took more than 2000 ms, then 'fallbackString()' method will be executed
	//And "Fallback started" will be returned
	//Sample output : Hello from hystrix service hystrix-hystrixClientPool-2
	//Sample output : Hello from hystrix service hystrix-hystrixClientPool-1
			
	@HystrixCommand(fallbackMethod="fallbackString",
	commandProperties= {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")},
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
	
	
	//For this method we have not defined thread pool
	//So it will use class Level "Main" thread pool.So 3 threads are available as defined on top of HystrixClientClass
	//If HYSTRIX-SERVICE which is another Spring project /Service took more than 6000 ms, then 'fallbackString()' method will be executed
	//And "Fallback started" will be returned
	//Sample output : Hello from hystrix service hystrix-mainPool-1
	//Sample output : Hello from hystrix service hystrix-mainPool-2
	//Sample output : Hello from hystrix service hystrix-mainPool-3
	
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
