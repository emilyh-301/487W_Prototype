package com.hotel.service.request;

import com.hotel.database.request.MaintenanceRequestDatabase;
import com.hotel.database.request.RequestWithNotesDatabase;
import com.hotel.model.request.AbstractRequest;
import com.hotel.model.request.RequestWithNotes;
import com.hotel.service.request.intf.RequestWithNotesServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MaintenanceRequestService extends AbstractRequestService implements RequestWithNotesServiceInterface {

    @Qualifier("Maintenance")
    private final MaintenanceRequestDatabase database;

    public MaintenanceRequestService(MaintenanceRequestDatabase database) {
        this.database = database;
    }

    @Override
    public void edit(long ID, int new_room, long new_time, String new_notes, String new_commonRequestType) {
        ((RequestWithNotesDatabase)database).edit(ID, new_room, new_time, new_notes, new_commonRequestType);
    }

    @Override
    public ArrayList<RequestWithNotes> findByCommonRequestType(String commonRequestType) {
        return ((RequestWithNotesDatabase)database).findByCommonRequestType(commonRequestType);
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
