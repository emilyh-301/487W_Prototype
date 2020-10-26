package com.hotel.service.request;

import com.hotel.database.request.WakeUpRequestDatabase;
import com.hotel.model.request.AbstractRequest;
import com.hotel.model.request.WakeUpRequest;
import com.hotel.service.request.intf.WakeUpRequestServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WakeUpRequestService extends AbstractRequestService implements WakeUpRequestServiceInterface {

    @Qualifier("Wakeup")
    private final WakeUpRequestDatabase database;

    public WakeUpRequestService(WakeUpRequestDatabase database) {
        this.database = database;
    }

    @Override
    public void edit(long ID, int new_room, long new_time, long new_wakeup_time) {
        ((WakeUpRequestDatabase)database).edit(ID, new_room, new_time, new_wakeup_time);
    }

    @Override
    public ArrayList<WakeUpRequest> findByWakeupTime(long wakeup_time) {
        return ((WakeUpRequestDatabase)database).findByWakeupTime(wakeup_time);
    }

    @Override
    public ArrayList<AbstractRequest> getDatabase() {
        return database.getDatabase();
    }

    @Override
    public void add(AbstractRequest request) {
        database.add(request);
    }

    @Override
    public void remove(AbstractRequest request) {
        database.remove(request);
    }

    @Override
    public void remove(long ID) {
        database.remove(ID);
    }

    @Override
    public AbstractRequest find(long ID) {
        return database.find(ID);
    }

    @Override
    public ArrayList<AbstractRequest> findRoom(int room) {
        return database.findRoom(room);
    }

    @Override
    public boolean exists(long ID) {
        return database.containsID(ID);
    }
}
