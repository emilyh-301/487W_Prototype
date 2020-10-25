package com.hotel.jpa;

import com.hotel.model.item.Cart;
import org.springframework.data.repository.CrudRepository;

public interface JpaCartRepository extends CrudRepository<Cart, Integer> {
}
