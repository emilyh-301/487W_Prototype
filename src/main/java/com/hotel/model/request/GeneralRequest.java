package com.hotel.model.request;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name =  "general_requests")
public class GeneralRequest extends RequestWithNotes {

    public GeneralRequest() {

    }

    public GeneralRequest(int id, int room, Date time, String notes, String commonRequestType) {
        super(id, room, time, notes, commonRequestType);
    }

    public GeneralRequest(int id, int room, long time, String notes, String commonRequestType) {
        super(id, room, time, notes, commonRequestType);
    }

}