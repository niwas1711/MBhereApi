
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Location {

    private List<Float> position = null;
    private Address address;

}
