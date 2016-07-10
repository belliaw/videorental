/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vr.videorental.model.mapper;

import java.util.ArrayList;
import java.util.List;
import org.vr.videorental.data.entities.Customer;
import org.vr.videorental.data.entities.Film;
import org.vr.videorental.model.CustomerModel;
import org.vr.videorental.model.FilmModel;

/**
 *
 * @author extwilbel
 */
public class ModelMapper {

    public static List<FilmModel> mapFilms(Iterable<Film> filmEntities) {
        if (filmEntities != null) {
            List<FilmModel> modelList = new ArrayList<>();

            for (Film filmEntity : filmEntities) {
                modelList.add(mapFilmEntityToModel(filmEntity));
            }

            return modelList;
        }

        return new ArrayList<>();
    }

    public static FilmModel mapFilmEntityToModel(Film film)
    {
        if(film != null)
            return new FilmModel(film.getName(), film.getRentalType().getName(), film.getFilmType().getType());

        return new FilmModel();
    }
    
     public static List<CustomerModel> mapCustomers(Iterable<Customer> customerEntities) {
        if (customerEntities != null) {
            List<CustomerModel> modelList = new ArrayList<>();

            for (Customer customerEntity : customerEntities) {
                modelList.add(mapCustomerEntityToModel(customerEntity));
            }

            return modelList;
        }

        return new ArrayList<>();
    }   
    
    public static CustomerModel mapCustomerEntityToModel(Customer customer)
    {
        if(customer != null)
            return new CustomerModel(customer.getName());

        return new CustomerModel();
    }    
}
