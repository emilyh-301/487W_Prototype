package com.hotel.service.staff;

import com.hotel.model.Staff;

public interface StaffServiceInterface {

    void add(Staff staff);

    Staff find(int id);

    Staff find(String username);

    boolean exists(int id);

    boolean exists(String username);

}
