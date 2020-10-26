package com.hotel.database.jpa;

import com.hotel.model.user.Permissions;
import com.hotel.model.user.Roles;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Set;

public interface JpaRolesRepository extends CrudRepository<Roles, Long> {

    @Query(value = "select p from Roles p")
    Collection<Roles> getAll(Sort s);

    @Query(value = "select p from Roles p where p.role_name = :name")
    Collection<Roles> getAll(@Param("name") String name, Sort s);

}
