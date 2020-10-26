package com.hotel.database.load;

import com.hotel.database.MemberDatabase;
import com.hotel.database.item.MenuItemDatabase;
import com.hotel.database.request.*;
import com.hotel.database.room.RoomDatabase;
import com.hotel.database.user.PrivilegeRepository;
import com.hotel.database.user.RoleRepository;
import com.hotel.database.user.staff.UserDatabase;
import com.hotel.model.item.MenuItem;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Privilege;
import com.hotel.model.user.Role;
import com.hotel.model.user.staff.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Called when the application is initialized or refreshed.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean privileges_already_setup = false;

    private final MemberDatabase database;

    private final UserDatabase userRepo;

    @Qualifier("Request")
    private final RequestDatabase requestRepo;

    @Qualifier("Maintenance")
    private final MaintenanceRequestDatabase maintenanceRepo;

    @Qualifier("General")
    private final GeneralRequestDatabase generalRepo;

    @Qualifier("Wakeup")
    private final WakeUpRequestDatabase wakeupRepo;

    private final MenuItemDatabase itemRepo;

    private final RoomDatabase roomRepo;

    private final PrivilegeRepository privRepo;

    private final RoleRepository roleRepo;

    public DatabaseLoader(MemberDatabase database, UserDatabase userRepo, RequestDatabase requestRepo,
                          MaintenanceRequestDatabase maintenanceRepo, GeneralRequestDatabase generalRepo,
                          WakeUpRequestDatabase wakeupRepo, MenuItemDatabase itemRepo, RoomDatabase roomRepo, PrivilegeRepository privRepo, RoleRepository roleRepo) {
        this.database = database;
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.generalRepo = generalRepo;
        this.wakeupRepo = wakeupRepo;
        this.itemRepo = itemRepo;
        this.roomRepo = roomRepo;
        this.privRepo = privRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /*
         * Set up privileges and roles
         */
        if(!privileges_already_setup) {
            Privilege guestPriv = createPrivilegeIfNotFound("GUEST_PRIVILEGES");
            Privilege staffPriv = createPrivilegeIfNotFound("STAFF_PRIVILEGES");

            createRoleIfNotFound("ROLE_GUEST", Collections.singletonList(guestPriv));
            createRoleIfNotFound("ROLE_STAFF", Arrays.asList(guestPriv, staffPriv));

            privileges_already_setup = true;
        }

        Staff s = new Staff();
        s.setUsername("test");
        s.setPassword("test");
        s.setRoles(roleRepo.find("ROLE_STAFF"));
        userRepo.addUser(s);

        ApplicationUser u = new ApplicationUser();
        u.setUsername("test_user");
        u.setPassword("test");
        u.setRoles(roleRepo.find("ROLE_GUEST"));
        userRepo.addUser(u);


        MenuItem n = new MenuItem();
        n.setId(1);
        n.setName("Good Food");
        n.setDescription("We sell good food");
        n.setPrice(13.4);
//        n.setImage("/images/food.jpg");
        itemRepo.add(n);
        n.setId(2);
        itemRepo.add(n);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privRepo.find(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privRepo.add(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepo.find(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepo.add(role);
        }
        return role;
    }
}
