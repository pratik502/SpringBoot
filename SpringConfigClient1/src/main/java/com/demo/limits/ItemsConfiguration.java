package com.demo.limits;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
//@ConfigurationProperties(prefix="item")  //This is working when property path file is local
public class ItemsConfiguration {

	@Value("${item1}") 
	private String item1;
	@Value("${item2}") 
	private String item2;
}
