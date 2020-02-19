package com.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discovery")
public class FilePathServiceDiscoveryClient {

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/get")
	public String getFileLocObject() {

		List<String> serviceIds = discoveryClient.getServices();
		String html ="";
		for (String serviceId : serviceIds) {
			 html+= "<br><h2>Instances for Service Id: " + serviceId + "</h2>";
			List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
			for (ServiceInstance serviceInstance : instances) {
				html += "<h3>Instance: " + serviceInstance.getUri() + "</h3>";
				html += "Host: " + serviceInstance.getHost() + "<br>";
				html += "Port: " + serviceInstance.getPort() + "<br>";
			}
		}
		return html;
	}

}