package com.hotel.service.user.staff;

import com.hotel.model.user.ApplicationUser;

public interface UserServiceInterface {

    void add(ApplicationUser user);

    ApplicationUser find(long id);

    ApplicationUser find(String username);

    boolean exists(long id);

    boolean exists(String username);

}
