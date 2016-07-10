/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vr.videorental.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vr.videorental.data.entities.*;
import org.vr.videorental.data.repository.*;
import org.vr.videorental.model.*;
import org.vr.videorental.model.mapper.ModelMapper;
import org.vr.videorental.utils.Utils;

/**
 *
 * @author extwilbel
 */
@RestController
public class Controller {

    @Autowired
    private CustomerRepository custRepo;
    
    @Autowired
    private FilmRepository filmRepo;

    @Autowired
    private FilmCustomerRepository filmCustomerRepo;

    @Autowired
    private RentalTypeRepository rentalTypeRepo;

    @Autowired
    private FilmTypeRepository filmTypeRepo;
    
    static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/customer/", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody CustomerModel customer) {
        LOG.info("Creating Customer {}",customer.getName());

        if (custRepo.findByName(customer.getName()) != null) {
            LOG.info("Customer {} already exists", customer.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Customer customerEntity = new Customer();
        customerEntity.setName(customer.getName());
        custRepo.save(customerEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/film/", method = RequestMethod.POST)
    public ResponseEntity<?> createFilm(@RequestBody FilmModel film) {
        LOG.info("Creating Film {} with type {}",film.getName(), film.getRentalType());

        RentalType rentalTypeEntity = rentalTypeRepo.findByName(film.getRentalType());
        FilmType filmTypeEntity = filmTypeRepo.findByType(film.getFilmType());
        
        if ( filmRepo.findByName(film.getName()) != null) {
            LOG.info("Film {} already exists", film.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if ( rentalTypeEntity == null || filmTypeEntity == null) {
            LOG.info("Type {} does not exist", film.getRentalType());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Film filmEntity = new Film();
        filmEntity.setName(film.getName());
        filmEntity.setRentalType(rentalTypeEntity);
        filmEntity.setFilmType(filmTypeEntity);
        filmRepo.save(filmEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomers() {
        LOG.info("Getting all customers");

        List<CustomerModel> modelList = ModelMapper.mapCustomers(custRepo.findAll());
        
        if(modelList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(modelList,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/film/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFilms() {
        LOG.info("Getting all films");

        List<FilmModel> modelList = ModelMapper.mapFilms(filmRepo.findAll());
        
        if(modelList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(modelList,HttpStatus.OK);
     }

    @RequestMapping(value = "/rent/", method = RequestMethod.POST)
    public ResponseEntity<?> rent(@RequestBody RentInputModel rentModel) {
        LOG.info("Renting films for customer {}", rentModel.getCustomerName());

        Customer customerEntity;
        if((customerEntity = custRepo.findByName(rentModel.getCustomerName())) == null) {
            LOG.info("Customer {} was not found", rentModel.getCustomerName());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        double rentalFee = 0.0;
        int bonusPointsAwarded = 0;
        Date issueDate;

        try {
            issueDate = Utils.parseDate(rentModel.getIssueDate());
        } catch (ParseException e) {
            LOG.info("Dates are in incorrect format");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        List<Film> filmEntityList = Utils.getFilmEntities(rentModel.getFilmNames(),filmRepo);
        if(filmEntityList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (Film film : filmEntityList) {
            rentalFee += (film.getRentalType().getFee() * rentModel.getDaysToRent());
            bonusPointsAwarded += film.getFilmType().getBonusPoints();

            FilmCustomer filmCustomerEntity = new FilmCustomer();
            filmCustomerEntity.setCustomer(customerEntity);
            filmCustomerEntity.setDaysRented(rentModel.getDaysToRent());
            filmCustomerEntity.setDateIssued(issueDate);
            filmCustomerEntity.setFilm(film);
            filmCustomerRepo.save(filmCustomerEntity);
        }

        return new ResponseEntity<>(new RentOutputModel(bonusPointsAwarded,rentalFee),HttpStatus.OK);
    }

    @RequestMapping(value = "/return/", method = RequestMethod.POST)
    public ResponseEntity<?> returnFilms(@RequestBody ReturnInputModel ruternModel) {
        LOG.info("Returning films for customer {}", ruternModel.getCustomerName());

        String customerName = ruternModel.getCustomerName();

        List<Film> filmEntityList = Utils.getFilmEntities(ruternModel.getFilmNames(),filmRepo);

        List<FilmCustomer> filmCustomerList = filmCustomerRepo.findByCustomerAndFilmInAndDateReturnedIsNull(
                custRepo.findByName(customerName),
                filmEntityList);

        double surcharge = 0.0;

        for(FilmCustomer filmCustomer : filmCustomerList) {
            int daysElapsed =
                    Days.daysBetween(
                            new DateTime(filmCustomer.getDateIssued()),new DateTime(new Date())).getDays();
            int daysRented = filmCustomer.getDaysRented();

            if(daysElapsed > daysRented)
                surcharge +=
                        (filmCustomer.getFilm().getRentalType().getFee() * (daysElapsed - daysRented));

            filmCustomer.setDateReturned(new Date());
            filmCustomerRepo.save(filmCustomer);
        }

        return new ResponseEntity<>(new ReturnOutputModel(surcharge),HttpStatus.OK);
    }
}
