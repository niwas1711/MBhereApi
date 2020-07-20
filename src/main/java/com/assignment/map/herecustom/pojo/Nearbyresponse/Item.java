
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import java.util.List;

import com.assignment.map.herecustom.pojo.AdditionalDatum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private List<Float> position = null;
    private Integer distance;
    private String title;
    private Integer averageRating;
    private Category category;
    private String icon;
    private String vicinity;
    private List<Object> having = null;
    private String type;
    private String href;
    private String id;
    private OpeningHours openingHours;
    private List<AlternativeName> alternativeNames = null;
    private List<Tag> tags = null;

}
