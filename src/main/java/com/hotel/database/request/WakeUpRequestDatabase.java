package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import com.hotel.database.request.intf.WakeUpRequestDatabaseInterface;
import com.hotel.model.request.WakeUpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Qualifier("Wakeup")
public class WakeUpRequestDatabase extends AbstractRequestDatabase implements WakeUpRequestDatabaseInterface {

    public WakeUpRequestDatabase(JpaRequestRepository repo) {
        super(repo);
    }

    @Override
    public void edit(long ID, int new_room, long new_time, long new_wakeup_time) {

        WakeUpRequest request = (WakeUpRequest) find(ID);

        if(request == null) return;

        repo.save(request.edit(new_room, new_time, new_wakeup_time));
    }

    @Override
    public ArrayList<WakeUpRequest> findByWakeupTime(long wakeup_time) {
        ArrayList<WakeUpRequest> requests = new ArrayList<>();

        repo.findAll().forEach(r -> {
            if(r instanceof WakeUpRequest && (((WakeUpRequest) r).getWakeup_time().getTime() == wakeup_time)) requests.add((WakeUpRequest) r);
        });

        return requests;
    }
}
