package com.hotel.model.request;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "wakeUpCallRequests")
public class WakeUpRequest extends AbstractRequest{

    @Column(name = "timeForCall")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wakeup_time;

    public WakeUpRequest() {

    }

    public WakeUpRequest(int id, int room, Date time, Date wakeup_time) {
        super(id, room, time);
        this.wakeup_time = wakeup_time;
    }

    public WakeUpRequest(int id, int room, long time, long wakeup_time) {
        super(id, room, time);
        this.wakeup_time = new Date(wakeup_time);
    }

    public WakeUpRequest edit(int new_room, long new_time, long new_wakeup_time) {

        if(new_room < AbstractRequest.MINIMUM_ROOM) this.room = AbstractRequest.MINIMUM_ROOM;
        else if(new_room > AbstractRequest.MAXIMUM_ROOM) this.room = AbstractRequest.MAXIMUM_ROOM;
        else this.room = new_room;

        setTime(new_time < AbstractRequest.MINIMUM_TIME? AbstractRequest.MINIMUM_TIME : new_time);
        setWakeup_time(new_wakeup_time < AbstractRequest.MINIMUM_TIME? AbstractRequest.MINIMUM_TIME : new_wakeup_time);

        return this;
    }

    //<editor-fold desc="Getters and Setters">
    public Date getWakeup_time() {
        return wakeup_time;
    }

    public void setWakeup_time(Date wakeup_time) {
        this.wakeup_time = wakeup_time;
    }

    public void setWakeup_time(long wakeup_time) {
        this.wakeup_time = new Date(wakeup_time);
    }
    //</editor-fold>
}
