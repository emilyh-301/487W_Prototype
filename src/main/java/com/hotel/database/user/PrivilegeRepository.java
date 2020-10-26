package com.hotel.database.user;

import com.hotel.database.jpa.JpaPrivilegeRepository;
import com.hotel.model.user.Privilege;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public class PrivilegeRepository implements PrivilegeRepositoryInterface {

    private final JpaPrivilegeRepository repo;

    public PrivilegeRepository(JpaPrivilegeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Collection<Privilege> getAll(Sort s) {
        return repo.getAll(s);
    }

    @Override
    public Privilege find(String name) {
        ArrayList<Privilege> p = new ArrayList<>(repo.getAll(name, Sort.unsorted()));

        return p.isEmpty()? null : p.get(0);
    }

    @Override
    public Privilege find(long id) {
        Optional<Privilege> p = repo.findById(id);

        return p.isPresent()? p.get() : null;
    }

    @Override
    public boolean exists(String name) {
        return find(name) != null;
    }

    @Override
    public boolean exists(long id) {
        return repo.existsById(id);
    }

    @Override
    public Privilege add(Privilege p) {
        if(p != null)
            return repo.save(p);
        return null;
    }

    @Override
    public void remove(Privilege p) {
        repo.delete(p);
    }

    @Override
    public void remove(long id) {
        repo.delete(find(id));
    }

    @Override
    public void remove(String name) {
        repo.delete(find(name));
    }
}
