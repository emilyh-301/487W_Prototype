package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("Maintenance")
public class MaintenanceRequestDatabase extends RequestWithNotesDatabase {

    public MaintenanceRequestDatabase(JpaRequestRepository repo) {
        super(repo);
    }
}
