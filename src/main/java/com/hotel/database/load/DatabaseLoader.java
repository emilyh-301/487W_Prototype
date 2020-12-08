package com.hotel.database.load;

import com.hotel.database.item.MenuItemDatabase;
import com.hotel.database.request.*;
import com.hotel.database.room.RoomDatabase;
import com.hotel.database.user.PermissionsDatabase;
import com.hotel.database.user.RolesDatabase;
import com.hotel.database.user.UserDatabase;
import com.hotel.model.item.Cart;
import com.hotel.model.item.MenuItem;
import com.hotel.model.request.Request;
import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Permissions;
import com.hotel.model.user.Roles;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

/**
 * Called when the application is initialized or refreshed.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean privileges_already_setup = false;


    private final UserDatabase userRepo;

    private final RequestDatabase requestRepo;

    private final MenuItemDatabase itemRepo;

    private final RoomDatabase roomRepo;

    private final PermissionsDatabase permRepo;

    private final RolesDatabase rolesRepo;


    public DatabaseLoader(UserDatabase userRepo, RequestDatabase requestRepo,
                          MenuItemDatabase itemRepo, RoomDatabase roomRepo, PermissionsDatabase permRepo, RolesDatabase rolesRepo) {
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
        this.itemRepo = itemRepo;
        this.roomRepo = roomRepo;

        this.permRepo = permRepo;
        this.rolesRepo = rolesRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /*
        //Generate rooms
        for(int i = Request.MINIMUM_ROOM; i <= Request.MAXIMUM_ROOM; i++) {
            roomRepo.addRoom(new Room(i));
        }
        */

        //Generate guests

        /*
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(Room r : roomRepo.getAll(Sort.unsorted())) {

            if(r == null) continue;
            if(r.getGuest() != null) continue;

            ApplicationUser u = new ApplicationUser(2L, r.getRoomString(3), encoder.encode(String.format("password%s", r.getRoomString(3))),
                    new HashSet<>(Collections.singleton(rolesRepo.find("ROLE_USER"))), new HashSet<>());
            userRepo.addUser(u);

        }

         */

        //Assign guests rooms
        /*
        for(Room r : roomRepo.getAll(Sort.unsorted())) {

            if(r == null) continue;
            if(r.getGuest() != null) continue;

            String un = r.getRoomString(3);

            ApplicationUser u = userRepo.find(un);

            if(u == null) continue;

            roomRepo.assignGuest(r, u);

        }

         */

//        /* Users already generated, don't uncomment
/*        if(!privileges_already_setup) {
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

        }*/

//         */

        /*
        MenuItem n = new MenuItem();
        n.setId(1);
        n.setName("Good Food");
        n.setDescription("We sell good food");
        n.setPrice(13.4);
        n.setImage("/images/food.jpg");
        itemRepo.add(n);
        n.setId(2);
        itemRepo.add(n);

         */
    }

}
