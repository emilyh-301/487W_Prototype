package com.hotel.model.repo.item;

import com.hotel.model.Allergen;
import com.hotel.model.MenuItem;

import java.util.ArrayList;
import java.util.Set;

public interface AllergenDatabaseInterface {

    Set<Allergen> getDatabase();

    void add(Allergen allergen);

    void remove(Allergen allergen);

    void remove(String id);

    Allergen find(String id);

    boolean contains(String id);

}
