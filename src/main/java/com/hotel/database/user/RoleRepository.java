package com.hotel.database.user;

import com.hotel.database.jpa.JpaRoleRepository;
import com.hotel.model.user.Privilege;
import com.hotel.model.user.Role;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public class RoleRepository implements RoleRepositoryInterface {

    private final JpaRoleRepository repo;

    private PrivilegeRepository privilegeRepository;

    public RoleRepository(JpaRoleRepository repo, PrivilegeRepository privilegeRepository) {
        this.repo = repo;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Collection<Role> getAll(Sort s) {
        return repo.getAll(s);
    }

    @Override
    public Role find(String name) {
        ArrayList<Role> p = new ArrayList<>(repo.getAll(name, Sort.unsorted()));

        return p.isEmpty()? null : p.get(0);
    }

    @Override
    public Role find(long id) {
        Optional<Role> p = repo.findById(id);

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
    public Role add(Role p) {
        if(p != null) {
            if(p.getPrivileges() != null) for(Privilege pr : p.getPrivileges()) privilegeRepository.add(pr);
            return repo.save(p);
        }
        return null;
    }

    @Override
    public void addPrivileges(@NotNull Role r, Privilege ... p) {
        for(Privilege pr : p) {
            if(!r.getPrivileges().contains(pr)) r.getPrivileges().add(pr);
        }
        if(exists(r.getRole_id())) repo.save(r);
    }

    @Override
    public void removePrivileges(@NotNull Role r, Privilege... p) {
        for(Privilege pr : p) {
            r.getPrivileges().remove(pr);
        }
        if(exists(r.getRole_id())) repo.save(r);
    }

    @Override
    public void remove(Role p) {
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
