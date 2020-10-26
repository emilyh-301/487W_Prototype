package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import com.hotel.model.request.AbstractRequest;

import java.util.ArrayList;
import java.util.Optional;

public abstract class AbstractRequestDatabase {

    protected JpaRequestRepository repo;

    public AbstractRequestDatabase(JpaRequestRepository repo) {
        this.repo = repo;
    }

    public ArrayList<AbstractRequest> getDatabase() {
        return (ArrayList<AbstractRequest>) repo.findAll();
    }

    public void add(AbstractRequest request) {
        if(request != null) repo.save(request);
    }

    public void remove(AbstractRequest request) {
        repo.delete(request);
    }

    public void remove(long ID) {
        repo.deleteById(ID);
    }

    public AbstractRequest find(long ID) {
        Optional<AbstractRequest> request = repo.findById(ID);

        return request.isPresent()? request.get() : null;
    }

    public ArrayList<AbstractRequest> findRoom(int room) {
        ArrayList<AbstractRequest> requests = new ArrayList<>();

        repo.findAll().forEach(r -> {
            if(r.getRoom() == room) requests.add(r);
        });

        return requests;
    }

    public boolean containsID(long ID) {
        return repo.existsById(ID);
    }
}
