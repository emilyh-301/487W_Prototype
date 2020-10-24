package com.hotel.model.repo.item;

import com.hotel.jpa.JpaAllergenRepository;
import com.hotel.model.Allergen;

import java.util.Optional;
import java.util.Set;

public class AllergenDatabase implements AllergenDatabaseInterface {

    /**
     * CRUD repository of allergens
     */
    private JpaAllergenRepository repo;

    @Override
    public Set<Allergen> getDatabase() {
        return (Set<Allergen>) repo.findAll();
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
