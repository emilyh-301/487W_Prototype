package com.hotel.model.request;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractRequest {

    /**
     * The request's unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_ID_SEQ")
    @SequenceGenerator(name = "REQUEST_ID_SEQ", sequenceName = "REQUEST_ID_SEQ", allocationSize = 100)
    @Column(name = "requestID")
    protected int id;

    /**
     * The room number that made the request
     */
    @Column(name = "roomNumber")
    protected int room;

    @Column(name = "timeOfRequest")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;

    public static final long MINIMUM_TIME = 1577836800000L; //January 1, 2020
    public static final int MINIMUM_ROOM = 0, MAXIMUM_ROOM = 310;

    public AbstractRequest() {

    }

    public AbstractRequest(int id, int room, Date time) {
        this.id = id;
        this.room = room;
        this.time = time;
    }

    public AbstractRequest(int id, int room, long time) {
        this.id = id;
        this.room = room;
        this.time = new Date(time);
    }

    //<editor-fold desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = new Date(time);
    }

    //</editor-fold>
}
