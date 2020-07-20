package com.assignment.map.herecustom.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.assignment.map.herecustom.Config.Propertiesreader;
import com.assignment.map.herecustom.Exceptions.PetrolStationNotfound;
import com.assignment.map.herecustom.Exceptions.ResturantNotfound;
import com.assignment.map.herecustom.Interface.Helpmate;
import com.assignment.map.herecustom.pojo.DisplayPosition;
import com.assignment.map.herecustom.pojo.GeoResponse;
import com.assignment.map.herecustom.pojo.Location;
import com.assignment.map.herecustom.pojo.Nearbyresponse.ApiRespone;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Category;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Item;
import com.assignment.map.herecustom.pojo.Nearbyresponse.NearbyResponse;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Parkingspots;
import com.assignment.map.herecustom.pojo.Nearbyresponse.PetrolStation;
import com.assignment.map.herecustom.pojo.Nearbyresponse.Resturant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Service
@Log
@EnableAsync
public class Helpmateimpl  {

	private final RestTemplate restTemplate;

	@Autowired
	private Propertiesreader props;

	public Helpmateimpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	
	final AtomicBoolean petrol = new AtomicBoolean(false);
	final AtomicBoolean parking = new AtomicBoolean(false);

	
	  @Cacheable("geocode")
	public HashMap<String, Float> geocode(String cityname) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(props.getGeolocationurl())
				.queryParam("apiKey", props.getHereapikey()).queryParam("searchtext", cityname);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		System.out.println("props.getHereapikey()\n" + builder.toUriString());

		HttpEntity<GeoResponse> geodata = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				GeoResponse.class);

		log.info("geo data values" + geodata.getBody().getResponse().getView().get(0).getResult().get(0).getLocation()
				.getDisplayPosition().getLatitude());
		log.info("geo data values" + geodata.getBody().getResponse().getView().get(0).getResult().get(0).getLocation()
				.getDisplayPosition().getLongitude());
		HashMap<String, Float> geolocation = new HashMap<String, Float>();

		geolocation.put("Latitude", geodata.getBody().getResponse().getView().get(0).getResult().get(0).getLocation()
				.getDisplayPosition().getLatitude());
		geolocation.put("Longitude", geodata.getBody().getResponse().getView().get(0).getResult().get(0).getLocation()
				.getDisplayPosition().getLongitude());

		return geolocation;
	}

	@Async
	  @Cacheable("resturantr")
	public CompletableFuture<List<Resturant>> restaurants(Float float1, Float float2) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(props.getNearbylocationurl())
				.queryParam("apiKey", props.getHereapikey()).queryParam("at", float1 + "," + float2);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		System.out.println("props.getHereapikey()\n" + builder.toUriString());

		HttpEntity<NearbyResponse> nearbyresponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				NearbyResponse.class);

		// List<String> categories = JsonPath.x.results.items[0].category.id

		log.info("nearbyresponse" + nearbyresponse.toString());
		// Object jsonPathValue = JsonPath.read(nearbyresponse,
		// "$restaurant.results.items[0].category.id");

		List<Item> completejason = nearbyresponse.getBody().getResults().getItems();

		Comparator<Item> sortbydistance = Comparator.comparing(e -> e.getDistance());

		List<Item> resturant = completejason.stream().sorted(sortbydistance)
				.filter(i -> i.getCategory().getId().equalsIgnoreCase("restaurant")).collect(Collectors.toList());

		
		  List<Item> Petrol =completejason.stream().sorted(sortbydistance)
					.filter(i -> i.getCategory().getId().equalsIgnoreCase("petrol-station")).collect(Collectors.toList());
		
		  if(Petrol.size()>1)
			  petrol.compareAndSet(false, true);

		
		
		log.info("resturant" + resturant.toString());

		List<Resturant> restresponse = new ArrayList();

		if (resturant.size() > 0) {
			for (int i = 0; i < 2; i++) {
				Resturant res = new Resturant();
				DisplayPosition location = new DisplayPosition();

				res.setDistance(resturant.get(i).getDistance());
				location.setLatitude(resturant.get(i).getPosition().get(0));
				location.setLongitude(resturant.get(i).getPosition().get(1));
				res.setLocation(location);

				res.setName(resturant.get(i).getTitle());
				restresponse.add(res);
			}
		} else
			throw new ResturantNotfound("No NearBy Resturant available");

		return CompletableFuture.completedFuture(restresponse);
	}

 	@Async
	  @Cacheable("Charging_Stations")
	public CompletableFuture<List<PetrolStation>> Charging_Stations(Float float1, Float float2) {

		// https://places.ls.hereapi.com/places/v1/discover/explore?at=52.5159%2C13.3777&cat=petrol-station&apiKey=H6XyiCT0w1t9GgTjqhRXxDMrVj9h78ya3NuxlwM7XUs

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		List<Item> petrolstation=new ArrayList();
		if(petrol.get())
		{
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(props.getPetrolstation())
				.queryParam("apiKey", props.getHereapikey()).queryParam("cat", "petrol-station")
				.queryParam("at", float1 + "," + float2);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		log.info("props.getHereapikey()\n" + builder.toUriString());

		HttpEntity<NearbyResponse> nearbyresponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				NearbyResponse.class);

	 
		List<Item> completeresp = nearbyresponse.getBody().getResults().getItems();

		Comparator<Item> sortbydistance = Comparator.comparing(e -> e.getDistance());

		 petrolstation = completeresp.stream().sorted(sortbydistance)
				.filter(i -> i.getCategory().getId().equalsIgnoreCase("petrol-station")).collect(Collectors.toList());
		}
		
		else
		{
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(props.getNearbylocationurl())
					.queryParam("apiKey", props.getHereapikey()).queryParam("at", float1 + "," + float2);

			HttpEntity<?> entity = new HttpEntity<>(headers);

			System.out.println("props.getHereapikey()\n" + builder.toUriString());

			HttpEntity<NearbyResponse> nearbyresponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					NearbyResponse.class);

 
			log.info("nearbyresponse" + nearbyresponse.toString());
			
			List<Item> completejason = nearbyresponse.getBody().getResults().getItems();

			Comparator<Item> sortbydistance = Comparator.comparing(e -> e.getDistance());

			 
			 petrolstation =completejason.stream().sorted(sortbydistance)
						.filter(i -> i.getCategory().getId().equalsIgnoreCase("petrol-station")).collect(Collectors.toList());
			
		}
		
		
		log.info("resturant" + petrolstation.toString());

		List<PetrolStation> petorolresponse = new ArrayList();

		if (petrolstation.size() > 0) {
			for (int i = 0; i < petrolstation.size(); i++) {
				PetrolStation petrolstationrsp = new PetrolStation();
				DisplayPosition location = new DisplayPosition();

				petrolstationrsp.setDistance(petrolstation.get(i).getDistance());
				location.setLatitude(petrolstation.get(i).getPosition().get(0));
				location.setLongitude(petrolstation.get(i).getPosition().get(1));
				petrolstationrsp.setLocation(location);

				petrolstationrsp.setName(petrolstation.get(i).getTitle());
				petorolresponse.add(petrolstationrsp);
			}
		} else
			throw new PetrolStationNotfound("No NearBy Petrol-station available");

		
		
		
		return CompletableFuture.completedFuture(petorolresponse);
	}

 	@Async
	  @Cacheable("parking")
 	public CompletableFuture<List<Parkingspots>> parking(Float latitude, Float longitude) {
		// https://places.ls.hereapi.com/places/v1/discover/search?at=37.7942%2C-122.4070&q=parking-facility&apiKey=H6XyiCT0w1t9GgTjqhRXxDMrVj9h78ya3NuxlwM7XUs

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(props.getParking())
				.queryParam("apiKey", props.getHereapikey()).queryParam("q", "parking-facility")
				.queryParam("at", latitude + "," + longitude);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		System.out.println("props.getHereapikey()\n" + builder.toUriString());

		HttpEntity<NearbyResponse> nearbyresponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				NearbyResponse.class);

		// List<String> categories = JsonPath.x.results.items[0].category.id

		System.out.println("nearbyresponse" + nearbyresponse.toString());
		// Object jsonPathValue = JsonPath.read(nearbyresponse,
		// "$restaurant.results.items[0].category.id");

		List<Item> completeresp = nearbyresponse.getBody().getResults().getItems();

		Comparator<Item> sortbydistance = Comparator.comparing(e -> e.getDistance());

		List<Item> parking = completeresp.stream().sorted(sortbydistance)
				.filter(i -> i.getCategory().getId().equalsIgnoreCase("parking-facility")).collect(Collectors.toList());

		log.info("resturant" + parking.toString());

		
		List<Parkingspots> parkingRespone = new ArrayList();

		if (parking.size() > 0) {
			for (int i = 0; i < 2; i++) {
				Parkingspots parkingst = new Parkingspots();
				DisplayPosition location = new DisplayPosition();

				parkingst.setDistance(parking.get(i).getDistance());
				location.setLatitude(parking.get(i).getPosition().get(0));
				location.setLongitude(parking.get(i).getPosition().get(1));
				parkingst.setLocation(location);

				parkingst.setName(parking.get(i).getTitle());
				parkingRespone.add(parkingst);
			}
		} else
			throw new PetrolStationNotfound("No NearBy parking place available");

		
		
		
		return CompletableFuture.completedFuture(parkingRespone);
	}

	

}
