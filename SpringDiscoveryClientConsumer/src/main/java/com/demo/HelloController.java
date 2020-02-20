package com.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
 
	@Autowired
	  private RestTemplate restTemplate;
	  
	@GetMapping("/get")
	  public FileLoc handleRequest() {
	    
	      FileLoc file = restTemplate.getForObject("http://FILE-PATH/get/path", FileLoc.class);
	     
	      return file;
	  }
}