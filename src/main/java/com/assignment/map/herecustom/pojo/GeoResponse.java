
package com.assignment.map.herecustom.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoResponse {
	@JsonProperty("Response")
    private Response Response;

    

}
