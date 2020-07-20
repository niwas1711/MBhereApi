
package com.assignment.map.herecustom.pojo.Nearbyresponse;

import java.util.List;

import com.assignment.map.herecustom.pojo.AdditionalDatum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class OpeningHours {

    private String text;
    private String label;
    private Boolean isOpen;
    private List<Structured> structured = null;

}
