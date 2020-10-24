package com.hotel.service.request.intf;

import com.hotel.model.request.WakeUpRequest;

import java.util.ArrayList;

public interface WakeUpRequestServiceInterface extends AbstractRequestServiceInterface {

    void edit(int ID, int new_room, long new_time, long new_wakeup_time);

    ArrayList<WakeUpRequest> findByWakeupTime(long wakeup_time);

}
