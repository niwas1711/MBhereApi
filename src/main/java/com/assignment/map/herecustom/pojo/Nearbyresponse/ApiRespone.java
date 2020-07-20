package com.assignment.map.herecustom.pojo.Nearbyresponse;

import java.util.List;

import lombok.Data;

@Data
public class ApiRespone {

	private List<Resturant> resturant;
	
	private List<PetrolStation> petrolStation;
	
	private List<Parkingspots> parkingspots;
	
}
