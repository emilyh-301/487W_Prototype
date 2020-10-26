package com.hotel.database.load;

import com.hotel.database.MemberDatabase;
import com.hotel.database.item.MenuItemDatabase;
import com.hotel.database.request.*;
import com.hotel.database.room.RoomDatabase;
import com.hotel.database.user.staff.UserDatabase;
import com.hotel.model.item.MenuItem;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.staff.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    public DatabaseLoader(MemberDatabase database, UserDatabase userRepo, RequestDatabase requestRepo,
                          MaintenanceRequestDatabase maintenanceRepo, GeneralRequestDatabase generalRepo,
                          WakeUpRequestDatabase wakeupRepo, MenuItemDatabase itemRepo, RoomDatabase roomRepo) {
        this.database = database;
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.generalRepo = generalRepo;
        this.wakeupRepo = wakeupRepo;
        this.itemRepo = itemRepo;
        this.roomRepo = roomRepo;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Staff staff = new Staff("test", encoder.encode("test"));

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
