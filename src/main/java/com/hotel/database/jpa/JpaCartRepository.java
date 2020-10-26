package com.hotel.database.jpa;

import com.hotel.model.item.Cart;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaCartRepository extends CrudRepository<Cart, Long> {

    @Query(value = "select c from Cart c")
    Collection<Cart> getAll(Sort s);

    @Query(value = "select c from Cart c where " +
            "(:room is null or c.room = :room) and " +
            "(:completed is null or c.completed = :completed)")
    Collection<Cart> getAll(@Param("room") Long room,
                            @Param("completed") Boolean completed,
                            Sort s);

}
