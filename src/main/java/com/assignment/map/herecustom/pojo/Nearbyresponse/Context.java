
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import com.assignment.map.herecustom.pojo.AdditionalDatum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Context {

    private Location location;
    private String type;
    private String href;

}
