package com.hotel.jpa;

import com.hotel.model.request.AbstractRequest;
import org.springframework.data.repository.CrudRepository;

public interface JpaRequestRepository extends CrudRepository<AbstractRequest, Integer> {
}
