package com.assignment.map.herecustom.Interface;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.EnableAsync;

import com.assignment.map.herecustom.pojo.GeoResponse;
import com.assignment.map.herecustom.pojo.Nearbyresponse.ApiRespone;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Item;
import com.assignment.map.herecustom.pojo.Nearbyresponse.NearbyResponse;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Parkingspots;
import com.assignment.map.herecustom.pojo.Nearbyresponse.PetrolStation;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Resturant;

public interface Helpmate {
	
	
HashMap<String, Float> geocode(String cityname);
	
 
	CompletableFuture<List<Resturant>>  restaurants(Float float1, Float float2);

	CompletableFuture<List<PetrolStation>>  Charging_Stations(Float float1, Float float2);

	CompletableFuture<List<Parkingspots>> parking(Float float1, Float float2);
	
	ApiRespone findnearby();
	

}
