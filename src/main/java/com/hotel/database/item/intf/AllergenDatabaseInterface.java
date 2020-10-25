package com.hotel.database.item.intf;

import com.hotel.model.item.Allergen;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface AllergenDatabaseInterface {

    Collection<Allergen> getDatabase(Sort sort);

    void add(Allergen allergen);

    void remove(Allergen allergen);

    void remove(String id);

    Allergen find(String id);

    boolean contains(String id);

}
