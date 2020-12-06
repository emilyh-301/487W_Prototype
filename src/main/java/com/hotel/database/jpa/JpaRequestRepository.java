package com.hotel.database.jpa;

import com.hotel.model.request.Request;
import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

/**
 * CRUD (Create, Read, Update, Delete) repository for requests
 */
public interface JpaRequestRepository extends CrudRepository<Request, Long> {

    @Query(value = "select r from Request r")
    Collection<Request> getAll(Sort s);

    @Query(value = "select r from Request r where " +
            "(:id is null or r.id = :id) and " +
            "(:room is null or r.room = :room) and " +
            "(:rtime is null or r.time = :rtime) and " +
            "(:completed is null or r.completed = :completed) and " +
            "(:notes is null or r.notes = :notes) and " +
            "(:commonRequestType is null or r.commonRequestType = :commonRequestType) and " +
            "(:wakeup_time is null or r.wakeup_time = :wakeup_time) and " +
            "(:requestType is null or r.requestType = :requestType)")
    Collection<Request> getAll(@Param("id") Long id, @Param("room") Integer room, @Param("rtime") Date time,
                               @Param("completed") Boolean completed, @Param("notes") String notes,
                               @Param("commonRequestType") String commonRequestType, @Param("wakeup_time") Date wakeup_time,
                               @Param("requestType") Request.Type requestType, Sort s);

}
