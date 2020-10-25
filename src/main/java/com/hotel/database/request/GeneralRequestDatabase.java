package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("General")
public class GeneralRequestDatabase extends RequestWithNotesDatabase {

    public GeneralRequestDatabase(JpaRequestRepository repo) {
        super(repo);
    }
}
