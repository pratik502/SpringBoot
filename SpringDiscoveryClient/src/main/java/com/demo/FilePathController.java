package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class FilePathController {
	
	@Autowired
	private Environment environment;
	
	
	@GetMapping("/path")
	public FileLoc getPath() {
		FileLoc fileLoc=new FileLoc();
		fileLoc.setLocation("D://Notes//");
		fileLoc.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return fileLoc;
	}
	
	

}
