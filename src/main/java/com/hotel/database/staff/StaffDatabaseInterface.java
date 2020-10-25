package com.hotel.database.staff;

import com.hotel.model.staff.Staff;

public interface StaffDatabaseInterface {

    Staff addStaff(Staff staff);

    Staff find(int ID);

    Staff find(String username);

    boolean containsID(int ID);

    boolean containsUser(String username);

}
