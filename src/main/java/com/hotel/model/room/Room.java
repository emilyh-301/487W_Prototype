package com.hotel.model.room;

import com.hotel.model.user.ApplicationUser;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "room")
    private int room;

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
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
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
