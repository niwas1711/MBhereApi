package com.assignment.map.herecustom.Exceptions;

public class PetrolStationNotfound extends RuntimeException {
	
	public String message;
	
	
	
	public PetrolStationNotfound(String message)
	{
		super(message);
		this.message=message;

	}
	
	

}
