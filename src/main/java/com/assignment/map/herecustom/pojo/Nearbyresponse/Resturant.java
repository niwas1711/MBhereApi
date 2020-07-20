package com.assignment.map.herecustom.pojo.Nearbyresponse;

import com.assignment.map.herecustom.pojo.DisplayPosition;
import com.assignment.map.herecustom.pojo.TopLeft;

import lombok.Data;

@Data
public class Resturant {

	
	private String name;
	private long distance;
	private DisplayPosition location;
}
