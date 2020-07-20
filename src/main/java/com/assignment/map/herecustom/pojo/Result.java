
package com.assignment.map.herecustom.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result {

	@JsonProperty("Relevance")
 private Float Relevance;
	@JsonProperty("MatchLevel")
 private String MatchLevel;
	@JsonProperty("MatchQuality")
 private MatchQuality MatchQuality;
	@JsonProperty("Location")
 private Location Location;

}
