package com.hotel.database.user.staff;

import com.hotel.database.jpa.JpaUserRepository;
import com.hotel.model.user.ApplicationUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserDatabase implements UserDatabaseInterface {

    private final JpaUserRepository repo;

    public UserDatabase(JpaUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public ApplicationUser addUser(ApplicationUser user) {
        return (user != null && !containsID(user.getUser_id()))? repo.save(user) : null;
    }

    @Override
    public ApplicationUser find(long ID) {
        Optional<ApplicationUser> user = repo.findById(ID);

        return user.isPresent()? user.get() : null;
    }

    @Override
    public ApplicationUser find(String username) {
        ArrayList<ApplicationUser> user = new ArrayList<>();

        repo.findAll().forEach(s -> {
            if(s.getUsername().equals(username)) user.add(s);
        });

        return user.isEmpty()? null : user.get(0);
    }

    @Override
    public boolean containsID(long ID) {
        return repo.existsById(ID);
    }

    @Override
    public boolean containsUser(String username) {
        return find(username) != null;
    }
}
