package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import com.hotel.model.request.Request;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public class RequestDatabase {

    private JpaRequestRepository repo;

    public RequestDatabase(JpaRequestRepository repo) {
        this.repo = repo;
    }

    public Collection<Request> getDatabase(Sort sort) {
        return repo.getAll(sort);
    }

    public Collection<Request> getMaintenanceDatabase(Sort sort) {
        return repo.getAll(null, null, null, null, null, null, null, Request.Type.wakeup, sort);
    }

    public Collection<Request> getGeneralDatabase(Sort sort) {
        return repo.getAll(null, null, null, null, null, null, null, Request.Type.wakeup, sort);
    }

    public Collection<Request> getWakeupDatabase(Sort sort) {
        return repo.getAll(null, null, null, null, null, null, null, Request.Type.wakeup, sort);
    }

    public void add(Request request) {
        if(request != null) repo.save(request);
    }

    public void edit(@NotNull long id, Integer new_room, Long new_time, String new_notes, String new_commonRequestType, Long new_wakeup_time, Request.Type new_type) {

        Request request = find(id);

        if(request == null) return;

        if(new_type != null) request.setRequestType(new_type);

        if(new_room != null && new_room >= Request.MINIMUM_ROOM && new_room <= Request.MAXIMUM_ROOM) request.setRoom(new_room);
        if(new_time != null && new_time >= Request.MINIMUM_TIME) request.setTime(new_time);

        if(request.getRequestType() != null && !request.getRequestType().equals(Request.Type.wakeup)) {
            if(new_notes != null) request.setNotes(new_notes);
            if(new_commonRequestType != null) request.setCommonRequestType(new_commonRequestType);
            request.setWakeup_time(null);
        }
        else if(request.getRequestType() != null && request.getRequestType().equals(Request.Type.wakeup)) {
            request.setNotes(null);
            request.setCommonRequestType(null);
            if(new_wakeup_time != null && new_wakeup_time >= Request.MINIMUM_TIME) request.setWakeup_time(new_wakeup_time);
        }

        repo.save(request);
    }

    public void setComplete(@NotNull long id, boolean complete) {

        Request request = find(id);

        if(request == null) return;

        request.setCompleted(complete);

        repo.save(request);
    }

    public void remove(Request request) {
        repo.delete(request);
    }

    public void remove(long id) {
        repo.deleteById(id);
    }

    public Request find(long id) {
        Optional<Request> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    public boolean contains(long id) {
        return repo.existsById(id);
    }

    public ArrayList<Request> findByCommonRequestType(String commonRequestType) {

        ArrayList<Request> requests = new ArrayList<>();

        repo.findAll().forEach(r -> {
            if(r.getCommonRequestType() != null && !r.getRequestType().equals(Request.Type.wakeup) && (r.getCommonRequestType().equals(commonRequestType) ||
                    r.getCommonRequestType().contains(commonRequestType))) requests.add(r);
        });

        return requests;

    }

    public ArrayList<Request> findByWakeupTime(long wakeup_time) {
        ArrayList<Request> requests = new ArrayList<>();

        repo.findAll().forEach(r -> {
            if(r.getWakeup_time() != null && r.getRequestType().equals(Request.Type.wakeup) &&
                    r.getWakeup_time().getTime() == wakeup_time) requests.add(r);
        });

        return requests;
    }


}
