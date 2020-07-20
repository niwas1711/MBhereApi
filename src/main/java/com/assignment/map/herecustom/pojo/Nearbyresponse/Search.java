
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import com.assignment.map.herecustom.pojo.AdditionalDatum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {

    private Context context;

}
