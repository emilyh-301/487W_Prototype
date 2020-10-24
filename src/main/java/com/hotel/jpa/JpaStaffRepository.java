package com.hotel.jpa;

import com.hotel.model.Staff;
import org.springframework.data.repository.CrudRepository;

public interface JpaStaffRepository extends CrudRepository<Staff, Integer> {
}
