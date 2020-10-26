package com.hotel.service.room;

import com.hotel.model.room.Room;

public interface RoomServiceInterface {

    Room addRoom(Room room);

    void removeRoom(Room room);

    void removeRoom(int room);

    Room find(int room_number);

    boolean exists(int room_number);

}
