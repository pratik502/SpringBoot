package com.demo.limits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Items")
public class ItemController {

	@Autowired
	public ItemsConfiguration itemConf;
	
	@GetMapping("/get")
	public Item getItemIngredients() {
		return new Item(itemConf.getItem1(),itemConf.getItem2());
	}
	
}
