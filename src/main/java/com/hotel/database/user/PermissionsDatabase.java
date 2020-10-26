package com.hotel.database.user;

import com.hotel.database.jpa.JpaPermissionsRepository;
import com.hotel.model.user.Permissions;
import com.hotel.model.user.Roles;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public class PermissionsDatabase {

    private final JpaPermissionsRepository repo;

    public PermissionsDatabase(JpaPermissionsRepository repo) {
        this.repo = repo;
    }

    public Collection<Permissions> getAll(Sort s) {
        return repo.getAll(s);
    }

    public Permissions add(Permissions p) {
        return p == null? null : repo.save(p);
    }

    public void remove(Permissions r) {
        repo.delete(r);
    }

    public void remove(long id) {
        repo.deleteById(id);
    }

    public boolean exists(long id) {
        return repo.existsById(id);
    }

    public Permissions find(long id) {
        Optional<Permissions> r = repo.findById(id);
        return r.isPresent()? r.get() : null;
    }

    public Permissions find(String name) {
        ArrayList<Permissions> r = new ArrayList<>(repo.getAll(name, Sort.unsorted()));
        return r.isEmpty()? null : r.get(0);
    }
}
