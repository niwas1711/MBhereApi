package com.assignment.map.herecustom.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.map.herecustom.Service.Helpmateimpl;
import com.assignment.map.herecustom.pojo.Nearbyresponse.ApiRespone;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Item;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Parkingspots;
import com.assignment.map.herecustom.pojo.Nearbyresponse.PetrolStation;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Resturant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/aide")
@Api(value = "Custom location", description = "Api's details for the Custom location")
public class ControllerCustom {
	
	@Autowired
	private Helpmateimpl servicelayer;
	
	@GetMapping(value="/location",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Get all Poi", response = ApiRespone.class, responseContainer = "ResponseEnity")
	public ResponseEntity<ApiRespone> fetchlocation(@RequestParam String location) throws InterruptedException, ExecutionException
	{
		HashMap<String,Float> geocode=  servicelayer.geocode(location);
		
		CompletableFuture<List<Resturant>> resturantrespone=servicelayer.restaurants(geocode.get("Latitude"), geocode.get("Longitude"));
	
		CompletableFuture<List<PetrolStation>> Charging_Stations=servicelayer.Charging_Stations(geocode.get("Latitude"), geocode.get("Longitude"));
		
		CompletableFuture<List<Parkingspots>> parking=servicelayer.parking(geocode.get("Latitude"), geocode.get("Longitude"));
		
		
		
		
		
		ApiRespone api= new ApiRespone();
		api.setParkingspots(parking.get());
		api.setPetrolStation(Charging_Stations.get());
		api.setResturant(resturantrespone.get());
		
		return new ResponseEntity<ApiRespone>(api,HttpStatus.OK);
			
	}

}
