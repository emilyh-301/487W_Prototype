package com.hotel.database.item;

import com.hotel.database.jpa.JpaAllergenRepository;
import com.hotel.model.item.Allergen;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class AllergenDatabase {

    /**
     * CRUD repository of allergens
     */
    private JpaAllergenRepository repo;

    public Collection<Allergen> getDatabase(Sort sort) {
        return repo.getAll(sort);
    }

    public void add(Allergen allergen) {
        if(allergen != null) repo.save(allergen);
    }

    public void remove(Allergen allergen) {
        repo.delete(allergen);
    }

    public void remove(String id) {
        repo.deleteById(id);
    }

    public Allergen find(String id) {
        Optional<Allergen> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    public boolean contains(String id) {
        return repo.existsById(id);
    }
}
