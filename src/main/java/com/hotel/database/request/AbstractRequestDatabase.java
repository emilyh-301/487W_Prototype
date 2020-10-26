package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import com.hotel.database.request.intf.AbstractRequestDatabaseInterface;
import com.hotel.model.request.AbstractRequest;

import java.util.ArrayList;
import java.util.Optional;

public abstract class AbstractRequestDatabase implements AbstractRequestDatabaseInterface {

    protected JpaRequestRepository repo;

    public AbstractRequestDatabase(JpaRequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public ArrayList<AbstractRequest> getDatabase() {
        return (ArrayList<AbstractRequest>) repo.findAll();
    }

    @Override
    public void add(AbstractRequest request) {
        if(request != null) repo.save(request);
    }

    @Override
    public void remove(AbstractRequest request) {
        repo.delete(request);
    }

    @Override
    public void remove(int ID) {
        repo.deleteById(ID);
    }

    @Override
    public AbstractRequest find(int ID) {
        Optional<AbstractRequest> request = repo.findById(ID);

        return request.isPresent()? request.get() : null;
    }

    @Override
    public ArrayList<AbstractRequest> findRoom(int room) {
        ArrayList<AbstractRequest> requests = new ArrayList<>();

        repo.findAll().forEach(r -> {
            if(r.getRoom() == room) requests.add(r);
        });

        return requests;
    }

    @Override
    public boolean containsID(int ID) {
        return repo.existsById(ID);
    }
}
