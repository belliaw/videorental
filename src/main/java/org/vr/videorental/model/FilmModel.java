/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vr.videorental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author extwilbel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmModel {
    private String name;
    private String rentalType;
    private String filmType;
}
