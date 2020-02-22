package com.demo;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hservice")
public class hystrixClass {

	@GetMapping("/get")
	public String get() {

		Random rand = new Random();
		int randomNum = rand.nextInt((3 - 1) + 1) + 1;
		//if (randomNum == 3)
			sleep();

		return "Hello from hystrix service";

	}

	private void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
