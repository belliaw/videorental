package org.vr.videorental.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.vr.videorental.data.entities.Film;
import org.vr.videorental.data.repository.FilmRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by William on 09/07/2016.
 */
public class Utils {

    public static List<Film> getFilmEntities(List<String> filmNames, FilmRepository filmRepository){

        List<Film> filmEntityList = new ArrayList<>();
        for (String filmName : filmNames) {

            Film filmEntity;

            if((filmEntity = filmRepository.findByName(filmName)) == null)
                return null;

            filmEntityList.add(filmEntity);
        }

        return filmEntityList;
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
        return sfd.parse(date);
    }


}
