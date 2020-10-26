package com.hotel.service.request;

import com.hotel.database.request.RequestDatabase;
import com.hotel.model.request.AbstractRequest;
import com.hotel.model.request.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RequestService extends AbstractRequestService {

    @Qualifier("Request")
    private RequestDatabase database;

    public RequestService(RequestDatabase database) {
        this.database = database;
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

    public void edit(long ID, int new_room, long new_time, Request.RequestType new_type) {

    }
}
