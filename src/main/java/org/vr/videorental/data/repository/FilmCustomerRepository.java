package org.vr.videorental.data.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.vr.videorental.data.entities.Customer;
import org.vr.videorental.data.entities.Film;
import org.vr.videorental.data.entities.FilmCustomer;

/**
 * The Film Customer repository interface
 *
 * @author: william.bellia
 * @since: 07/07/2016 18:21
 */

public interface FilmCustomerRepository extends CrudRepository<FilmCustomer, Long>
{
    List<FilmCustomer> findByFilm(Film film);
    
    List<FilmCustomer> findByCustomer(Customer customer);

    List<FilmCustomer> findByCustomerAndFilmInAndDateReturnedIsNull(Customer customer, List<Film> films);
}