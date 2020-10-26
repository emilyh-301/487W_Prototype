package com.hotel.database.jpa;

import com.hotel.model.item.Cart;
import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaCartRepository extends CrudRepository<Cart, Long> {

    @Query(value = "select c from Cart c")
    Collection<Cart> getAll(Sort s);

    @Query(value = "select c from Cart c where " +
            "(:user is null or c.user = :user) and " +
            "(:completed is null or c.completed = :completed)")
    Collection<Cart> getAll(@Param("user") ApplicationUser user,
                            @Param("completed") Boolean completed,
                            Sort s);

}
