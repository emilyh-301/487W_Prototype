package com.hotel.service.request;

import com.hotel.database.request.RequestDatabase;
import com.hotel.model.request.Request;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class RequestService {

    private RequestDatabase database;

    public RequestService(RequestDatabase database) {
        this.database = database;
    }

    public Collection<Request> getDatabase(Sort sort) {
        return database.getDatabase(sort);
    }

    public Collection<Request> getMaintenanceDatabase(Sort sort) {
        return database.getMaintenanceDatabase(sort);
    }

    public Collection<Request> getGeneralDatabase(Sort sort) {
        return database.getGeneralDatabase(sort);
    }

    public Collection<Request> getWakeupDatabase(Sort sort) {
        return database.getWakeupDatabase(sort);
    }

    public void add(Request request) {
        database.add(request);
    }

    public void edit(@NotNull long id, Integer new_room, Long new_time, String new_notes, String new_commonRequestType, Long new_wakeup_time, Request.Type new_type) {
        database.edit(id, new_room, new_time, new_notes, new_commonRequestType, new_wakeup_time, new_type);
    }

    public void setComplete(@NotNull long id, boolean complete) {
        database.setComplete(id, complete);
    }

    public void remove(Request request) {
        database.remove(request);
    }

    public void remove(long id) {
        database.remove(id);
    }

    public Request find(long id) {
        return database.find(id);
    }

    public boolean contains(long id) {
        return database.contains(id);
    }

    public ArrayList<Request> findByCommonRequestType(String commonRequestType) {

        return database.findByCommonRequestType(commonRequestType);

    }

    public ArrayList<Request> findByWakeupTime(long wakeup_time) {
        return database.findByWakeupTime(wakeup_time);
    }

}
