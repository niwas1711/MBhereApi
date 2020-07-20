package com.assignment.map.herecustom.pojo.Nearbyresponse;

import com.assignment.map.herecustom.pojo.DisplayPosition;
import com.assignment.map.herecustom.pojo.TopLeft;

import lombok.Data;


@Data
public class PetrolStation {
	private String name;
	private Integer distance;
	private DisplayPosition location;
}
