package com.hotel.jpa;

import com.hotel.model.item.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface JpaCartItemRepository extends CrudRepository<CartItem, Integer> {
}
