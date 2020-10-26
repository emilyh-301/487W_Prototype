package com.hotel.database.jpa;

import com.hotel.model.user.Privilege;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaPrivilegeRepository extends CrudRepository<Privilege, Long> {

    @Query(value = "select p from Privilege p")
    Collection<Privilege> getAll(Sort s);

    @Query(value = "select p from Privilege p where p.name = :name")
    Collection<Privilege> getAll(@Param("name") String name, Sort s);
}
