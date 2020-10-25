package com.hotel.jpa;

import com.hotel.model.staff.Staff;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * CRUD (Create, Read, Update, Delete) repository for staff members
 */
public interface JpaStaffRepository extends CrudRepository<Staff, Integer> {

    @Query(value = "select s from Staff s")
    Collection<Staff> getAll(Sort s);

    @Query(value = "select s from Staff s where s.username = :username")
    Collection<Staff> getAll(@Param("username") String username);

}
