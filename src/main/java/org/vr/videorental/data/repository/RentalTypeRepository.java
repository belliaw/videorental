package org.vr.videorental.data.repository;

import org.springframework.data.repository.CrudRepository;

import org.vr.videorental.data.entities.RentalType;

/**
 * The RentalType repository interface
 *
 * @author: william.bellia
 * @since: 07/07/2016 18:18
 */

public interface RentalTypeRepository extends CrudRepository<RentalType, Long>
{
    RentalType findByName(String name);
}