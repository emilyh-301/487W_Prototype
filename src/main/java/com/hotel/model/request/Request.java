package com.hotel.model.request;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "request")
public class Request extends AbstractRequest {

    /**
     * The request type
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "requestType")
    private RequestType type;

    public Request() {

    }

    public Request(int id, int room, Date time, RequestType type) {
        super(id, room, time);
        this.type = type;
    }

    public Request(int id, int room, long time, RequestType type) {
        super(id, room, time);
        this.type = type;
    }

    public Request edit(int new_room, long new_time, RequestType new_type) {

        if(new_room < AbstractRequest.MINIMUM_ROOM) this.room = AbstractRequest.MINIMUM_ROOM;
        else if(new_room > AbstractRequest.MAXIMUM_ROOM) this.room = AbstractRequest.MAXIMUM_ROOM;
        else this.room = new_room;

        setTime(new_time < AbstractRequest.MINIMUM_TIME? AbstractRequest.MINIMUM_TIME : new_time);

        this.type = new_type;

        return this;
    }

    //<editor-fold desc="Getters and Setters">
    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }
    //</editor-fold>

    public enum RequestType {
        Wakeup, Maintenance, General
    }
}
