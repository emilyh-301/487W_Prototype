package com.hotel.database.request.intf;

import com.hotel.model.request.AbstractRequest;

import java.util.ArrayList;

public interface AbstractRequestDatabaseInterface {

    ArrayList<AbstractRequest> getDatabase();

    void add(AbstractRequest request);

    void remove(AbstractRequest request);

    void remove(long ID);

    AbstractRequest find(long ID);

    ArrayList<AbstractRequest> findRoom(int room);

    boolean containsID(long ID);
}
