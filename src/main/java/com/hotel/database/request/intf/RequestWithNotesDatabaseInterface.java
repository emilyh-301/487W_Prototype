package com.hotel.database.request.intf;

import com.hotel.model.request.RequestWithNotes;

import java.util.ArrayList;

public interface RequestWithNotesDatabaseInterface extends AbstractRequestDatabaseInterface {

    void edit(int ID, int new_room, long new_time, String new_notes, String new_commonRequestType);

    ArrayList<RequestWithNotes> findByCommonRequestType(String commonRequestType);

}
