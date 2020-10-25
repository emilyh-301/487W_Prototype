package com.hotel.model.repo.item;

import com.hotel.jpa.JpaAllergenRepository;
import com.hotel.model.item.Allergen;
import com.hotel.model.repo.item.intf.AllergenDatabaseInterface;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public class AllergenDatabase implements AllergenDatabaseInterface {

    /**
     * CRUD repository of allergens
     */
    private JpaAllergenRepository repo;

    @Override
    public Collection<Allergen> getDatabase(Sort sort) {
        return repo.getAll(sort);
    }

    @Override
    public void add(Allergen allergen) {
        if(allergen != null) repo.save(allergen);
    }

    @Override
    public void remove(Allergen allergen) {
        repo.delete(allergen);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Allergen find(String id) {
        Optional<Allergen> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    @Override
    public boolean contains(String id) {
        return repo.existsById(id);
    }
}
