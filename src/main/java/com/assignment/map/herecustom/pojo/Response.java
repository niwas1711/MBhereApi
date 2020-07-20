
package com.assignment.map.herecustom.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Response {

	@JsonProperty("MetaInfo")
private MetaInfo MetaInfo;
	@JsonProperty("View")
private List<View> View = null;

   
}
