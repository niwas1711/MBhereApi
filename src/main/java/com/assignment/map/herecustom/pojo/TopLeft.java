
package com.assignment.map.herecustom.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TopLeft {

	@JsonProperty("Latitude")
private Float Latitude;
	@JsonProperty("Longitude")
 private Float Longitude;

   
}
