package com.hotel.database.user;

import com.hotel.database.jpa.JpaUserRepository;
import com.hotel.model.user.ApplicationUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserDatabase {

    private final JpaUserRepository repo;

    public UserDatabase(JpaUserRepository repo) {
        this.repo = repo;
    }

    public ApplicationUser addUser(ApplicationUser user) {
        return (user != null)? repo.save(user) : null;
    }

    public ApplicationUser find(long ID) {
        Optional<ApplicationUser> user = repo.findById(ID);

        return user.isPresent()? user.get() : null;
    }

    public ApplicationUser find(String username) {
        ArrayList<ApplicationUser> user = new ArrayList<>();

        repo.findAll().forEach(s -> {
            if(s.getUsername().equals(username)) user.add(s);
        });

        return user.isEmpty()? null : user.get(0);
    }

    public boolean containsID(long ID) {
        return repo.existsById(ID);
    }

    public boolean containsUser(String username) {
        return find(username) != null;
    }
}
