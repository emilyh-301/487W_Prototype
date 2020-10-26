package com.hotel.database.user;

import com.hotel.database.jpa.JpaRolesRepository;
import com.hotel.model.user.Permissions;
import com.hotel.model.user.Roles;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RolesDatabase {

    private final JpaRolesRepository repo;

    private final PermissionsDatabase permissionsDatabase;

    public RolesDatabase(JpaRolesRepository repo, PermissionsDatabase permissionsDatabase) {
        this.repo = repo;
        this.permissionsDatabase = permissionsDatabase;
    }

    public Collection<Roles> getAll(Sort s) {
        return repo.getAll(s);
    }

    public Roles add(Roles r) {
        if(r != null) {
            if(r.getRole_permissions() != null) {
                for(Permissions p : r.getRole_permissions()) if(p.getPermission_id() == null || !permissionsDatabase.exists(p.getPermission_id())) permissionsDatabase.add(p);
            }
            else r.setRole_permissions(new HashSet<>());

            return repo.save(r);
        }

        return null;
    }

    public void addPermissions(Roles r, Permissions ... p) {
        if(r == null) return;
        if(r.getRole_permissions() == null) r.setRole_permissions(new HashSet<>());

        for(Permissions pr : p) {
            if(pr.getPermission_id() == null || !permissionsDatabase.exists(pr.getPermission_id())) permissionsDatabase.add(pr);
            r.getRole_permissions().add(pr);
        }

        repo.save(r);
    }

    public void removePermissions(Roles r, Permissions ... p) {
        if(r == null || r.getRole_permissions() == null || r.getRole_permissions().isEmpty()) return;

        r.getRole_permissions().removeAll(Arrays.asList(p));

        repo.save(r);
    }

    public void remove(Roles r) {
        repo.delete(r);
    }

    public void remove(long id) {
        repo.deleteById(id);
    }

    public boolean exists(long id) {
        return repo.existsById(id);
    }

    public Roles find(long id) {
        Optional<Roles> r = repo.findById(id);
        return r.isPresent()? r.get() : null;
    }

    public Roles find(String name) {
        ArrayList<Roles> r = new ArrayList<>(repo.getAll(name, Sort.unsorted()));
        return r.isEmpty()? null : r.get(0);
    }
}
