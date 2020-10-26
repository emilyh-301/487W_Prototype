package com.hotel.service.room;

import com.hotel.database.room.RoomDatabase;
import com.hotel.model.room.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements RoomServiceInterface {

    private final RoomDatabase database;

    public RoomService(RoomDatabase database) {
        this.database = database;
    }

    @Override
    public Room addRoom(Room room) {
        return database.addRoom(room);
    }

    @Override
    public void removeRoom(Room room) {
        database.removeRoom(room);
    }

    @Override
    public void removeRoom(int room) {
        database.removeRoom(room);
    }

    @Override
    public Room find(int room_number) {
        return database.find(room_number);
    }

    @Override
    public boolean exists(int room_number) {
        return database.exists(room_number);
    }
}
