package com.hotel.database.jpa;

import com.hotel.model.item.Allergen;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface JpaAllergenRepository extends CrudRepository<Allergen, String> {

    @Query("select a from Allergen a")
    Collection<Allergen> getAll(Sort sort);
}
