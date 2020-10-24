package com.hotel.model.request;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class RequestWithNotes extends AbstractRequest {

    @Column(name = "notes")
    protected String notes;

    @Column(name = "commonRequestType")
    protected String commonRequestType;

    public RequestWithNotes() {

    }

    public RequestWithNotes(int id, int room, Date time, String notes, String commonRequestType) {
        super(id, room, time);
        this.notes = notes;
        this.commonRequestType = commonRequestType;
    }

    public RequestWithNotes(int id, int room, long time, String notes, String commonRequestType) {
        super(id, room, time);
        this.notes = notes;
        this.commonRequestType = commonRequestType;
    }

    public RequestWithNotes edit(int new_room, long new_time, String new_notes, String new_commonRequestType) {

        if(new_room < AbstractRequest.MINIMUM_ROOM) this.room = AbstractRequest.MINIMUM_ROOM;
        else if(new_room > AbstractRequest.MAXIMUM_ROOM) this.room = AbstractRequest.MAXIMUM_ROOM;
        else this.room = new_room;

        setTime(new_time < AbstractRequest.MINIMUM_TIME? AbstractRequest.MINIMUM_TIME : new_time);

        this.notes = new_notes == null? "" : new_notes;
        this.commonRequestType = new_commonRequestType == null? "" : new_commonRequestType;

        return this;
    }

    //<editor-fold desc="Getters and Setters">
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCommonRequestType() {
        return commonRequestType;
    }

    public void setCommonRequestType(String commonRequestType) {
        this.commonRequestType = commonRequestType;
    }
    //</editor-fold>
}
