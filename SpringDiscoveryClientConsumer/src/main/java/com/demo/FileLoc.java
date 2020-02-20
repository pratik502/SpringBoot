package com.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileLoc {

	private String name;
	private String location;
	private double size;
	private int port;
	private String content;
}
