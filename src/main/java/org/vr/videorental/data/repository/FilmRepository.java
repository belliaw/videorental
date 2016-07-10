package org.vr.videorental.data.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.vr.videorental.data.entities.Customer;
import org.vr.videorental.data.entities.Film;

/**
 * The Film repository interface
 *
 * @author: william.bellia
 * @since: 07/07/2016 18:03
 */

public interface FilmRepository extends CrudRepository<Film, Long>
{
    Film findByName(String name);
}