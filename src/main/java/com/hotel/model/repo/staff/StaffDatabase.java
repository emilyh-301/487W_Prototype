package com.hotel.model.repo.staff;

import com.hotel.jpa.JpaStaffRepository;
import com.hotel.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class StaffDatabase implements StaffDatabaseInterface {

    private final JpaStaffRepository repo;

    public StaffDatabase(JpaStaffRepository repo) {
        this.repo = repo;
    }

    @Override
    public Staff addStaff(Staff staff) {
        return (staff != null && !containsID(staff.getId()))? repo.save(staff) : null;
    }

    @Override
    public Staff find(int ID) {
        Optional<Staff> staff = repo.findById(ID);

        return staff.isPresent()? staff.get() : null;
    }

    @Override
    public Staff find(String username) {
        ArrayList<Staff> staff = new ArrayList<>();

        repo.findAll().forEach(s -> {
            if(s.getUsername().equals(username)) staff.add(s);
        });

        return staff.isEmpty()? null : staff.get(0);
    }

    @Override
    public boolean containsID(int ID) {
        return repo.existsById(ID);
    }

    @Override
    public boolean containsUser(String username) {
        return find(username) != null;
    }
}
