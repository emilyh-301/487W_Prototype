package com.hotel.jpa;

import com.hotel.model.item.Allergen;
import org.springframework.data.repository.CrudRepository;

public interface JpaAllergenRepository extends CrudRepository<Allergen, String> {
}
