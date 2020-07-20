
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import java.util.List;

import com.assignment.map.herecustom.pojo.AdditionalDatum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString
@EqualsAndHashCode
public class Results {

    private String next;
    private List<Item> items = null;

}
