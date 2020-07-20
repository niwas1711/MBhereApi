
package com.assignment.map.herecustom.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	@JsonProperty("Label")
	private String Label;

	@JsonProperty("Country")
	private String Country;
	@JsonProperty("State")
	private String State;

	@JsonProperty("County")
	private String County;
	@JsonProperty("City")
	private String City;
	@JsonProperty("PostalCode")
	private String PostalCode;

	@JsonProperty("AdditionalData")
	private List<AdditionalDatum> AdditionalData = null;

}
