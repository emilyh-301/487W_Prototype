package com.hotel.database.user.staff;

import com.hotel.model.user.ApplicationUser;

public interface UserDatabaseInterface {

    ApplicationUser addUser(ApplicationUser user);

    ApplicationUser find(long id);

    ApplicationUser find(String username);

    boolean containsID(long id);

    boolean containsUser(String username);

}
