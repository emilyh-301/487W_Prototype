package com.hotel.database.jpa;

import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaRoomRepository extends CrudRepository<Room, Integer> {

    @Query(value = "select r from Room r")
    Collection<Room> getAll(Sort s);

    @Query(value = "select r from Room r where r.guest = :guest")
    Collection<Room> getAll(@Param("guest") ApplicationUser g, Sort s);

    @Query(value = "select r from Room r where " +
            "(r.guest is null and :occupied = false) or " +
            "(r.guest is not null and :occupied = true)")
    Collection<Room> getAll(@Param("occupied") boolean occupied, Sort s);

}
