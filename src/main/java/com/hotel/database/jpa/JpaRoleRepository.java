package com.hotel.database.jpa;

import com.hotel.model.user.Privilege;
import com.hotel.model.user.Role;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaRoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "select p from Role p")
    Collection<Role> getAll(Sort s);

    @Query(value = "select p from Role p where p.name = :name")
    Collection<Role> getAll(@Param("name") String name, Sort s);
}
