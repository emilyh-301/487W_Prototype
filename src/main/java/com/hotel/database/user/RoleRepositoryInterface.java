package com.hotel.database.user;

import com.hotel.model.user.Privilege;
import com.hotel.model.user.Role;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface RoleRepositoryInterface {

    Collection<Role> getAll(Sort s);

    Role find(String name);

    Role find(long id);

    boolean exists(String name);

    boolean exists(long id);

    Role add(Role p);

    void addPrivileges(@NotNull Role r, Privilege ... p);

    void removePrivileges(@NotNull Role r, Privilege ... p);

    void remove(Role p);

    void remove(long id);

    void remove(String name);

}
