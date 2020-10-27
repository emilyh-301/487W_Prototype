package com.hotel.database.jpa;

import com.hotel.model.room.Room;
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

    @Query(value = "select s from ApplicationUser s where " +
            "(:username is null or s.username = :username) and " +
            "(:room is null or s.room = :room)")
    Collection<ApplicationUser> getAll(@Param("username") String username, @Param("room") Room room, Sort s);

}
