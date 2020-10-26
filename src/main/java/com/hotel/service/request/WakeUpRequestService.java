package com.hotel.service.request;

import com.hotel.database.request.WakeUpRequestDatabase;
import com.hotel.model.request.AbstractRequest;
import com.hotel.model.request.WakeUpRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WakeUpRequestService extends AbstractRequestService {

    @Qualifier("Wakeup")
    private final WakeUpRequestDatabase database;

    public WakeUpRequestService(WakeUpRequestDatabase database) {
        this.database = database;
    }

    public void edit(long ID, int new_room, long new_time, long new_wakeup_time) {
        ((WakeUpRequestDatabase)database).edit(ID, new_room, new_time, new_wakeup_time);
    }

    public ArrayList<WakeUpRequest> findByWakeupTime(long wakeup_time) {
        return ((WakeUpRequestDatabase)database).findByWakeupTime(wakeup_time);
    }

    public ArrayList<AbstractRequest> getDatabase() {
        return database.getDatabase();
    }

    public void add(AbstractRequest request) {
        database.add(request);
    }

    public void remove(AbstractRequest request) {
        database.remove(request);
    }

    public void remove(long ID) {
        database.remove(ID);
    }

    public AbstractRequest find(long ID) {
        return database.find(ID);
    }

    public ArrayList<AbstractRequest> findRoom(int room) {
        return database.findRoom(room);
    }

    public boolean exists(long ID) {
        return database.containsID(ID);
    }
}
