package org.vr.videorental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by William on 09/07/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentInputModel {
    private String customerName;
    private List<String> filmNames;
    private String issueDate;
    private int daysToRent;
}
