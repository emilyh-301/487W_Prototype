package com.hotel.model.user;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_ID_SEQ")
    @SequenceGenerator(name = "ROLE_ID_SEQ", sequenceName = "ROLE_ID_SEQ", allocationSize = 250)
    private Long role_id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<ApplicationUser> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "privilege_id"))
    private Collection<Privilege> privileges;

    public Role() {

    }

    public Role(Long role_id, String name, Collection<ApplicationUser> users, Collection<Privilege> privileges) {
        this.role_id = role_id;
        this.name = name;
        this.users = users;
        this.privileges = privileges;
    }

    public Role(String name, Collection<ApplicationUser> users, Collection<Privilege> privileges) {
        this.name = name;
        this.users = users;
        this.privileges = privileges;
    }

    public Role(String name) {
        this.name = name;
    }

    //<editor-fold desc="Getters and Setters">
    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long id) {
        this.role_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<ApplicationUser> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
    //</editor-fold>
}
