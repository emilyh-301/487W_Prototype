package com.hotel.jpa;

import com.hotel.model.request.AbstractRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD (Create, Read, Update, Delete) repository for requests
 */
public interface JpaRequestRepository extends CrudRepository<AbstractRequest, Integer> {
}
