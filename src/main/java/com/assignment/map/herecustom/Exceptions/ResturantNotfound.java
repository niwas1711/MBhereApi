package com.assignment.map.herecustom.Exceptions;

public class ResturantNotfound extends RuntimeException{
	
	
	public String Message;
	
	
	 
	
	public ResturantNotfound(String Message)
	{
	    super(Message);
	    this.Message=Message;
	   
	    
		
	}


}
