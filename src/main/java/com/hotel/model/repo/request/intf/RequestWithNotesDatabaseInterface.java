package com.hotel.model.repo.request.intf;

import com.hotel.model.request.RequestWithNotes;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface RequestWithNotesDatabaseInterface extends AbstractRequestDatabaseInterface {

    void edit(int ID, int new_room, long new_time, String new_notes, String new_commonRequestType);

    ArrayList<RequestWithNotes> findByCommonRequestType(String commonRequestType);

}
