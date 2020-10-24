package com.hotel.jpa;

import com.hotel.model.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface JpaMenuItemRepository extends CrudRepository<MenuItem, Integer> {
}
