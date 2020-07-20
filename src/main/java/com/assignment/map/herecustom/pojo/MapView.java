
package com.assignment.map.herecustom.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MapView {
	@JsonProperty("TopLeft")
    private TopLeft TopLeft;
   
		@JsonProperty("BottomRight")
		private BottomRight BottomRight;

   
}
