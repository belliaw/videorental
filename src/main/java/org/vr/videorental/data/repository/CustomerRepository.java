package org.vr.videorental.data.repository;

import org.springframework.data.repository.CrudRepository;

import org.vr.videorental.data.entities.Customer;

/**
 * The Customer repository interface
 *
 * @author: william.bellia
 * @since: 07/07/2016 18:06
 */

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    Customer findByName(String name);
}