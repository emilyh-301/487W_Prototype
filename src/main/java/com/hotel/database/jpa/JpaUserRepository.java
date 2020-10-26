package com.hotel.database.jpa;

import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * CRUD (Create, Read, Update, Delete) repository for staff members
 */
public interface JpaUserRepository extends CrudRepository<ApplicationUser, Long> {

    @Query(value = "select s from ApplicationUser s")
    Collection<ApplicationUser> getAll(Sort s);

    @Query(value = "select s from ApplicationUser s where s.username = :username")
    Collection<ApplicationUser> getAll(@Param("username") String username, Sort s);

}
