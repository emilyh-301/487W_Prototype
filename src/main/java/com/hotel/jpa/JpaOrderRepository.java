package com.hotel.jpa;

import com.hotel.model.item.Order;
import org.springframework.data.repository.CrudRepository;

public interface JpaOrderRepository extends CrudRepository<Order, Integer> {
}
