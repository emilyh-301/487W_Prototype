package com.hotel.service.user.staff;

import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.staff.Staff;
import com.hotel.database.user.staff.UserDatabase;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    private UserDatabase database;

    public UserService(UserDatabase database) {
        this.database = database;
    }

    @Override
    public void add(ApplicationUser staff) {
        database.addUser(staff);
    }

    @Override
    public ApplicationUser find(long id) {
        return database.find(id);
    }

    @Override
    public ApplicationUser find(String username) {
        return database.find(username);
    }

    @Override
    public boolean exists(long id) {
        return database.containsID(id);
    }

    @Override
    public boolean exists(String username) {
        return database.containsUser(username);
    }
}
