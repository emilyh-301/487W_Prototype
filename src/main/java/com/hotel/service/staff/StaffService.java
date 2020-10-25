package com.hotel.service.staff;

import com.hotel.model.staff.Staff;
import com.hotel.database.staff.StaffDatabase;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements StaffServiceInterface {

    private StaffDatabase database;

    @Override
    public void add(Staff staff) {
        database.addStaff(staff);
    }

    @Override
    public Staff find(int id) {
        return database.find(id);
    }

    @Override
    public Staff find(String username) {
        return database.find(username);
    }

    @Override
    public boolean exists(int id) {
        return database.containsID(id);
    }

    @Override
    public boolean exists(String username) {
        return database.containsUser(username);
    }
}
