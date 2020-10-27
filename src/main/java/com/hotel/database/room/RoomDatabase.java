package com.hotel.database.room;

import com.hotel.database.jpa.JpaCartRepository;
import com.hotel.database.jpa.JpaRoomRepository;
import com.hotel.database.jpa.JpaUserRepository;
import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public class RoomDatabase {

    private final JpaRoomRepository repo;

    private final JpaUserRepository userRepository;

    public RoomDatabase(JpaRoomRepository repo, JpaUserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }

    public Collection<Room> getAll(Sort s) {
        return repo.getAll(s);
    }

    public Room addRoom(Room room) {
        return (room != null)? repo.save(room) : null;
    }

    public void removeRoom(Room room) {
        repo.delete(room);
    }

    public void removeRoom(int room) {
        repo.deleteById(room);
    }

    public Room find(int room_number) {
        Optional<Room> room = repo.findById(room_number);

        return room.isPresent()? room.get() : null;
    }

    public Room find(ApplicationUser g) {
        ArrayList<Room> r = new ArrayList<>(repo.getAll(g, Sort.unsorted()));

        return r.isEmpty()? null : r.get(0);
    }

    public Collection<Room> find(boolean occupied, Sort s) {
        return repo.getAll(occupied, s);
    }

    public boolean exists(int room_number) {
        return repo.existsById(room_number);
    }

    public void assignGuest(Room room, ApplicationUser g) {
        room.setGuest(g);
        g.setRoom(room);
        repo.save(room);
        userRepository.save(g);
    }

}
