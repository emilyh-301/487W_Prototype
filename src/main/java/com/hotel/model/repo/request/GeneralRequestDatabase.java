package com.hotel.model.repo.request;

import com.hotel.jpa.JpaRequestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("General")
public class GeneralRequestDatabase extends RequestWithNotesDatabase {

    public GeneralRequestDatabase(JpaRequestRepository repo) {
        super(repo);
    }
}
