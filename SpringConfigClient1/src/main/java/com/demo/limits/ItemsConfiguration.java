package com.demo.limits;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
//@ConfigurationProperties(prefix = "items")  //This is working when property path file is local
public class ItemsConfiguration {

	@Value("${item1}") 
	private String item1;
	@Value("${item2}") 
	private String item2;
}
