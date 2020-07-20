
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import java.util.List;

import com.assignment.map.herecustom.pojo.AdditionalDatum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Address {

    private String text;
    private String house;
    private String street;
    private String postalCode;
    private String district;
    private String city;
    private String county;
    private String stateCode;
    private String country;
    private String countryCode;
    
    
	

}
