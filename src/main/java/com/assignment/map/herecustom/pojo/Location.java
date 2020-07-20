
package com.assignment.map.herecustom.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class Location {

	@JsonProperty("LocationId")
    private String LocationId;
	
	@JsonProperty("LocationType")
     private String LocationType;
	@JsonProperty("DisplayPosition")
      private DisplayPosition DisplayPosition;
    
	@JsonProperty("NavigationPosition")

	private List<NavigationPosition> NavigationPosition = null;
	@JsonProperty("MapView")
	private MapView MapView;
	@JsonProperty("Address")
	private Address Address;

  
}
