package com.hotel.database.jpa;

import com.hotel.model.item.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface JpaOrderRepository extends CrudRepository<Order, Integer> {

    @Query("select o from Order o")
    Collection<Order> getAll(Sort s);

    @Query("select o from Order o where " +
            "(:cart_id is null or o.cart.id = :cart_id) and " +
            "(:status is null or o.status = :status) and " +
            "(:time is null or o.time = :time)")
    Collection<Order> getAll(@Param("cart_id") Integer cart_id, @Param("status") Order.Status status, @Param("time") Date date, Sort s);

    @Query("select o from Order o where o.time < :time")
    Collection<Order> getAllBeforeTime(@Param("time") Date date, Sort s);

    @Query("select o from Order o where o.time > :time")
    Collection<Order> getAllAfterTime(@Param("time") Date date, Sort s);

    @Query("select o from Order o where o.time between :time_min and :time_max")
    Collection<Order> getAllBetweenTimes(@Param("time_min") Date date_min, @Param("time_max") Date date_max, Sort s);

}
