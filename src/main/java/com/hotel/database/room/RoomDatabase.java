package com.hotel.database.room;

import com.hotel.database.jpa.JpaRoomRepository;
import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public class RoomDatabase implements RoomDatabaseInterface {

    private final JpaRoomRepository repo;

    public RoomDatabase(JpaRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public Collection<Room> getAll(Sort s) {
        return repo.getAll(s);
    }

    @Override
    public Room addRoom(Room room) {
        return (room != null && !repo.existsById(room.getRoom()))? repo.save(room) : null;
    }

    @Override
    public void removeRoom(Room room) {
        repo.delete(room);
    }

    @Override
    public void removeRoom(int room) {
        repo.deleteById(room);
    }

    @Override
    public Room find(int room_number) {
        Optional<Room> room = repo.findById(room_number);

        return room.isPresent()? room.get() : null;
    }

    @Override
    public Room find(ApplicationUser g) {
        ArrayList<Room> r = new ArrayList<>(repo.getAll(g, Sort.unsorted()));

        return r.isEmpty()? null : r.get(0);
    }

    @Override
    public Collection<Room> find(boolean occupied, Sort s) {
        return repo.getAll(occupied, s);
    }

    @Override
    public boolean exists(int room_number) {
        return repo.existsById(room_number);
    }

    @Override
    public void assignGuest(Room room, ApplicationUser g) {
        room.setGuest(g);
        repo.save(room);
    }
}
