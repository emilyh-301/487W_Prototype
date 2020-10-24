package com.hotel.model.request;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name =  "maintenance_requests")
public class MaintenanceRequest extends RequestWithNotes {

    public MaintenanceRequest() {

    }

    public MaintenanceRequest(int id, int room, Date time, String notes, String commonRequestType) {
        super(id, room, time, notes, commonRequestType);
    }

    public MaintenanceRequest(int id, int room, long time, String notes, String commonRequestType) {
        super(id, room, time, notes, commonRequestType);
    }

}
