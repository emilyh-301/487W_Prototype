package com.hotel.database.user;

import com.hotel.model.user.Privilege;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface PrivilegeRepositoryInterface {

    Collection<Privilege> getAll(Sort s);

    Privilege find(String name);

    Privilege find(long id);

    boolean exists(String name);

    boolean exists(long id);

    Privilege add(Privilege p);

    void remove(Privilege p);

    void remove(long id);

    void remove(String name);

}
