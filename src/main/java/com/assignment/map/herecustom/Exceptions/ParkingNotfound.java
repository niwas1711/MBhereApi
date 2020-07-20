package com.assignment.map.herecustom.Exceptions;

public class ParkingNotfound extends RuntimeException{
	
	
	
	public String message;
	
	ParkingNotfound(String message)
	{
		
		
		super(message);
		
	}
	

}
