package org.vr.videorental.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.vr.videorental.data.entities.FilmType;
import org.vr.videorental.data.entities.RentalType;

/**
 * Created by William on 09/07/2016.
 */
public interface FilmTypeRepository extends CrudRepository<FilmType, Long>
{
    FilmType findByType(String type);
}
