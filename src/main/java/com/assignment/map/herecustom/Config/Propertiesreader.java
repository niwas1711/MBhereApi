package com.assignment.map.herecustom.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

 
import lombok.Data;
import lombok.ToString;

@Component
@ConfigurationProperties("aide")
@Data
@ToString
public class Propertiesreader {

	private String hereapikey;
	
	private String geolocationurl;
	
	private String nearbylocationurl;
	
	private String petrolstation;
	
	private String parking;
}
