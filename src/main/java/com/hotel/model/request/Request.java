package com.hotel.model.request;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name =  "request")
public class Request {

    /**
     * The request's unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQ_ID_SEQ")
    @SequenceGenerator(name = "REQ_ID_SEQ", sequenceName = "REQ_ID_SEQ", allocationSize = 100)
    @Column(name = "requestID")
    protected Long id;

    /**
     * The room number that made the request
     */
    @Column(name = "roomNumber")
    protected int room;

    /**
     * The time the request was made
     */
    @Column(name = "timeOfRequest")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;

    /**
     * Whether or not the request is completed
     */
    @Column(name = "completed")
    protected boolean completed;

    /**
     * Notes the guest made on the request
     */
    @Column(name = "notes")
    protected String notes;

    /**
     * The common name for the request, if the request
     * is pre-defined
     */
    @Column(name = "commonRequestType")
    protected String commonRequestType;

    /**
     * The time that the guest wants to be awoken, if this
     * is a wake-up call
     */
    @Column(name = "timeForCall")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date wakeup_time;

    @Column(name = "requestType")
    @Enumerated(EnumType.STRING)
    protected Type requestType;

    public static final long MINIMUM_TIME = 1577836800000L; //January 1, 2020
    public static final int MINIMUM_ROOM = 0, MAXIMUM_ROOM = 310;

    public Request() {
    }

    public Request(Long id, int room, Date time, boolean completed, String notes, String commonRequestType, Date wakeup_time, Type requestType) {
        this.id = id;
        this.room = room;
        this.time = time;
        this.completed = completed;
        this.notes = notes;
        this.commonRequestType = commonRequestType;
        this.wakeup_time = wakeup_time;
        this.requestType = requestType;
    }

    public static Request createGeneralRequest(Long id, int room, Date time, String notes, String commonRequestType) {
        return new Request(id, room, time, false, notes, commonRequestType, null, Type.general);
    }

    public static Request createMaintenanceRequest(Long id, int room, Date time, String notes, String commonRequestType) {
        return new Request(id, room, time, false, notes, commonRequestType, null, Type.maintenance);
    }

    public static Request createWakeUpRequest(Long id, int room, Date time, Date wakeup_time) {
        return new Request(id, room, time, false, null, null, wakeup_time, Type.wakeup);
    }

    //<editor-fold desc="Getters and Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getWakeup_time() {
        return wakeup_time;
    }

    public void setWakeup_time(Date wakeup_time) {
        this.wakeup_time = wakeup_time;
    }

    public void setWakeup_time(long time) {
        this.wakeup_time = new Date(time);
    }

    public Type getRequestType() {
        return requestType;
    }

    public void setRequestType(Type requestType) {
        this.requestType = requestType;
    }
    //</editor-fold>

    public enum Type {
        maintenance, general, wakeup,
    }

}
