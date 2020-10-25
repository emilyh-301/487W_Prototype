package com.hotel.database.request;

import com.hotel.database.jpa.JpaRequestRepository;
import com.hotel.database.request.intf.RequestDatabaseInterface;
import com.hotel.model.request.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("Request")
public class RequestDatabase extends AbstractRequestDatabase implements RequestDatabaseInterface {

    public RequestDatabase(JpaRequestRepository repo) {
        super(repo);
    }

    @Override
    public void edit(int ID, int new_room, long new_time, Request.RequestType new_type) {

        Request request = (Request) find(ID);

        if(request == null) return;

        repo.save(request.edit(new_room, new_time, new_type));

    }
}
