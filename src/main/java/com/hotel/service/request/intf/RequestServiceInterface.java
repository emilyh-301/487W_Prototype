package com.hotel.service.request.intf;

import com.hotel.model.request.Request;

public interface RequestServiceInterface extends AbstractRequestServiceInterface {

    void edit(int ID, int new_room, long new_time, Request.RequestType new_type);

}
