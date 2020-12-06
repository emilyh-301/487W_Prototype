package com.hotel.model.request;

import org.hibernate.annotations.CreationTimestamp;

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
    protected Long id;

    /**
     * The room number that made the request
     */
    @Column(name = "roomNumber")
    protected int room;

    @Column(name = "timeOfRequest")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;

    @Column(name = "completed")
    protected boolean completed;

    public static final long MINIMUM_TIME = 1577836800000L; //January 1, 2020
    public static final int MINIMUM_ROOM = 0, MAXIMUM_ROOM = 310;

    public AbstractRequest() {

    }

    public AbstractRequest(long id, int room, Date time) {
        this.id = id;
        this.room = room;
        this.time = time;
        this.completed = false;
    }

    public AbstractRequest(long id, int room, long time) {
        this.id = id;
        this.room = room;
        this.time = new Date(time);
        this.completed = false;
    }

    public AbstractRequest(int room, Date time) {
        this.room = room;
        this.time = time;
        this.completed = false;
    }

    public AbstractRequest(int room, long time) {
        this.room = room;
        this.time = new Date(time);
        this.completed = false;
    }

    //<editor-fold desc="Getters and Setters">
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    //</editor-fold>
}
