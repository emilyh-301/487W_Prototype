package com.hotel.model.repo.request;

import com.hotel.jpa.JpaRequestRepository;
import com.hotel.model.repo.request.intf.RequestWithNotesDatabaseInterface;
import com.hotel.model.request.RequestWithNotes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public class RequestWithNotesDatabase extends AbstractRequestDatabase implements RequestWithNotesDatabaseInterface {

    public RequestWithNotesDatabase(JpaRequestRepository repo) {
        super(repo);
    }

    @Override
    public void edit(int ID, int new_room, long new_time, String new_notes, String new_commonRequestType) {

        RequestWithNotes request = (RequestWithNotes) find(ID);

        if(request == null) return;

        repo.save(request.edit(new_room, new_time, new_notes, new_commonRequestType));

    }

    @Override
    public ArrayList<RequestWithNotes> findByCommonRequestType(String commonRequestType) {

        ArrayList<RequestWithNotes> requests = new ArrayList<>();

        repo.findAll().forEach(r -> {
            if(r instanceof RequestWithNotes && (((RequestWithNotes)r).getCommonRequestType().equals(commonRequestType) ||
                    ((RequestWithNotes)r).getCommonRequestType().contains(commonRequestType))) requests.add((RequestWithNotes) r);
        });

        return requests;

    }
}
