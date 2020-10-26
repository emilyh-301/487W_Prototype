package com.hotel.model.room;

import com.hotel.model.item.Cart;
import com.hotel.model.user.ApplicationUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "room")
    private Integer room;

    @OneToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser guest;

    public Room() {

    }

    public Room(int room) {
        this.room = room;
    }

    public Room(int room, ApplicationUser guest) {
        this.room = room;
        this.guest = guest;
    }

    public boolean isOccupied() {
        return guest != null;
    }

    //<editor-fold desc="Getters and Setters">
    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public ApplicationUser getGuest() {
        return guest;
    }

    public void setGuest(ApplicationUser guest) {
        this.guest = guest;
    }

    //</editor-fold>

}
