package com.hotel.model.repo.request.intf;

import com.hotel.model.request.Request;

public interface RequestDatabaseInterface {

    void edit(int ID, int new_room, long new_time, Request.RequestType new_type);

}
