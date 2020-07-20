
package com.assignment.map.herecustom.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class View {

	@JsonProperty("_type")
private String _type;
	@JsonProperty("ViewId")
private Integer ViewId;
	
	@JsonProperty("Result")
private List<Result> Result = null;

   
}
