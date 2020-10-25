package com.hotel.jpa;

import com.hotel.model.staff.Staff;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD (Create, Read, Update, Delete) repository for staff members
 */
public interface JpaStaffRepository extends CrudRepository<Staff, Integer> {
}
