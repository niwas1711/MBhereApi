
package com.assignment.map.herecustom.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MatchQuality {
	
	@JsonProperty("City")
    private Float City;

   
}
