package com.hotel.jpa;

import com.hotel.model.MenuItem;
import org.springframework.data.repository.CrudRepository;

/**
 * CRUD (Create, Read, Update, Delete) repository for menu items
 */
public interface JpaMenuItemRepository extends CrudRepository<MenuItem, Integer> {
}
