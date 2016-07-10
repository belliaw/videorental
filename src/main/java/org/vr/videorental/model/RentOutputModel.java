package org.vr.videorental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by William on 09/07/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentOutputModel {
    private int bonusPointsForRent;
    private double totalFee;
}
