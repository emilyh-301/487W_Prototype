package com.hotel.database.jpa;

import com.hotel.model.user.Permissions;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaPermissionsRepository extends CrudRepository<Permissions, Long> {

    @Query(value = "select p from Permissions p")
    Collection<Permissions> getAll(Sort s);

    @Query(value = "select p from Permissions p where p.permission_name = :name")
    Collection<Permissions> getAll(@Param("name") String name, Sort s);

}
