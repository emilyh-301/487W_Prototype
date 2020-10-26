package com.hotel.service.room;

import com.hotel.database.room.RoomDatabase;
import com.hotel.model.room.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomDatabase database;

    public RoomService(RoomDatabase database) {
        this.database = database;
    }

    public Room addRoom(Room room) {
        return database.addRoom(room);
    }

    public void removeRoom(Room room) {
        database.removeRoom(room);
    }

    public void removeRoom(int room) {
        database.removeRoom(room);
    }

    public Room find(int room_number) {
        return database.find(room_number);
    }

    public boolean exists(int room_number) {
        return database.exists(room_number);
    }
}
