package com.hotel.model.repo.request.intf;

import com.hotel.model.request.WakeUpRequest;

import java.util.ArrayList;

public interface WakeUpRequestDatabaseInterface extends AbstractRequestDatabaseInterface {

    void edit(int ID, int new_room, long new_time, long new_wakeup_time);

    ArrayList<WakeUpRequest> findByWakeupTime(long wakeup_time);

}
