package com.hotel.database.room;

import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public interface RoomDatabaseInterface {

    Collection<Room> getAll(Sort s);

    Room addRoom(Room room);

    void removeRoom(Room room);

    void removeRoom(int room);

    Room find(int room_number);

    Room find(ApplicationUser g);

    Collection<Room> find(boolean occupied, Sort s);

    boolean exists(int room_number);

    void assignGuest(Room room, ApplicationUser g);

}
