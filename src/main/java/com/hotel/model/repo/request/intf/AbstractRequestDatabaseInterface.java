package com.hotel.model.repo.request.intf;

import com.hotel.model.request.AbstractRequest;

import java.util.ArrayList;

public interface AbstractRequestDatabaseInterface {

    ArrayList<AbstractRequest> getDatabase();

    void add(AbstractRequest request);

    void remove(AbstractRequest request);

    void remove(int ID);

    AbstractRequest find(int ID);

    ArrayList<AbstractRequest> findRoom(int room);

    boolean containsID(int ID);
}
