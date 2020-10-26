package com.hotel.database.load;

import com.hotel.database.MemberDatabase;
import com.hotel.database.item.MenuItemDatabase;
import com.hotel.database.request.*;
import com.hotel.database.room.RoomDatabase;
import com.hotel.database.user.PermissionsDatabase;
import com.hotel.database.user.RolesDatabase;
import com.hotel.database.user.staff.UserDatabase;
import com.hotel.model.item.MenuItem;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Permissions;
import com.hotel.model.user.Roles;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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

    private final PermissionsDatabase permRepo;

    private final RolesDatabase rolesRepo;


    public DatabaseLoader(MemberDatabase database, UserDatabase userRepo, RequestDatabase requestRepo,
                          MaintenanceRequestDatabase maintenanceRepo, GeneralRequestDatabase generalRepo,
                          WakeUpRequestDatabase wakeupRepo, MenuItemDatabase itemRepo, RoomDatabase roomRepo, PermissionsDatabase permRepo, RolesDatabase rolesRepo) {
        this.database = database;
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.generalRepo = generalRepo;
        this.wakeupRepo = wakeupRepo;
        this.itemRepo = itemRepo;
        this.roomRepo = roomRepo;

        this.permRepo = permRepo;
        this.rolesRepo = rolesRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /* Users already generated, don't uncomment
        if(!privileges_already_setup) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


            rolesRepo.add(new Roles("ROLE_STAFF", new Permissions("STAFF_PERMISSION")));
            rolesRepo.add(new Roles("ROLE_USER", new Permissions("USER_PERMISSION")));

            Roles staff = rolesRepo.find("ROLE_STAFF");
            Roles user = rolesRepo.find("ROLE_USER");

            ApplicationUser s = new ApplicationUser();
            s.setUser_id(0);
            s.setUsername("staff");
            s.setPassword(encoder.encode("password"));
            s.setUser_roles(staff);
            userRepo.addUser(s);

            ApplicationUser g = new ApplicationUser();
            g.setUser_id(1);
            g.setUsername("guest");
            g.setPassword(encoder.encode("password"));
            g.setUser_roles(user);
            userRepo.addUser(g);


            privileges_already_setup = true;

        }

         */

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

}
