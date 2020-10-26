package com.hotel.database.load;

import com.hotel.database.MemberDatabase;
import com.hotel.database.item.MenuItemDatabase;
import com.hotel.database.request.*;
import com.hotel.database.staff.StaffDatabase;
import com.hotel.model.item.MenuItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * Called when the application is initialized or refreshed.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final MemberDatabase database;

    private final StaffDatabase staffRepo;

    @Qualifier("Request")
    private final RequestDatabase requestRepo;

    @Qualifier("Maintenance")
    private final MaintenanceRequestDatabase maintenanceRepo;

    @Qualifier("General")
    private final GeneralRequestDatabase generalRepo;

    @Qualifier("Wakeup")
    private final WakeUpRequestDatabase wakeupRepo;

    private final MenuItemDatabase itemRepo;

    public DatabaseLoader(MemberDatabase database, StaffDatabase staffRepo, RequestDatabase requestRepo,
                          MaintenanceRequestDatabase maintenanceRepo, GeneralRequestDatabase generalRepo,
                          WakeUpRequestDatabase wakeupRepo, MenuItemDatabase itemRepo) {
        this.database = database;
        this.staffRepo = staffRepo;
        this.requestRepo = requestRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.generalRepo = generalRepo;
        this.wakeupRepo = wakeupRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
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
