
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import com.assignment.map.herecustom.pojo.AdditionalDatum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Category {

    private String id;
    private String title;
    private String href;
    private String type;
    private String system;

}
