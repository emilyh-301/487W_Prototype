package com.hotel.model.repo.request;

import com.hotel.jpa.JpaRequestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("Maintenance")
public class MaintenanceRequestDatabase extends RequestWithNotesDatabase {

    public MaintenanceRequestDatabase(JpaRequestRepository repo) {
        super(repo);
    }
}
