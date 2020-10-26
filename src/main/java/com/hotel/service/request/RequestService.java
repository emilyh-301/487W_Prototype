package com.hotel.service.request;

import com.hotel.database.request.RequestDatabase;
import com.hotel.model.request.AbstractRequest;
import com.hotel.model.request.Request;
import com.hotel.service.request.intf.RequestServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RequestService extends AbstractRequestService implements RequestServiceInterface {

    @Qualifier("Request")
    private RequestDatabase database;

    public RequestService(RequestDatabase database) {
        this.database = database;
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

    @Override
    public void edit(long ID, int new_room, long new_time, Request.RequestType new_type) {

    }
}
