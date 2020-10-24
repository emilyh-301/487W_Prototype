package com.hotel.service.request.intf;

import com.hotel.model.request.AbstractRequest;

import java.util.ArrayList;

public interface AbstractRequestServiceInterface {

    ArrayList<AbstractRequest> getDatabase();

    void add(AbstractRequest request);

    void remove(AbstractRequest request);

    void remove(int ID);

    AbstractRequest find(int ID);

    ArrayList<AbstractRequest> findRoom(int room);

    boolean exists(int ID);

}
