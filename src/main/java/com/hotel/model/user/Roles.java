package com.hotel.model.user;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_ID_SEQ")
    @SequenceGenerator(name = "ROLE_ID_SEQ", sequenceName = "ROLE_ID_SEQ", allocationSize = 250)
    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "role_name")
    private String role_name;

    @ManyToMany
    @JoinTable(name = "role_permissions", joinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "role_id"), inverseJoinColumns =
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"))
    private Set<Permissions> role_permissions;

    public Roles() {

    }

    public Roles(Long role_id, String role_name, Set<Permissions> role_permissions) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_permissions = role_permissions;
    }

    public Roles(String role_name, Set<Permissions> role_permissions) {
        this.role_name = role_name;
        this.role_permissions = role_permissions;
    }

    public Roles(String role_name, Permissions ... role_permissions) {
        this.role_name = role_name;
        this.role_permissions = new HashSet<>(Arrays.asList(role_permissions));
    }

    public Roles(String role_name) {
        this.role_name = role_name;
        this.role_permissions = new HashSet<>();
    }

    //<editor-fold desc="Getters and Setters">
    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Set<Permissions> getRole_permissions() {
        return role_permissions;
    }

    public void setRole_permissions(Set<Permissions> role_permissions) {
        this.role_permissions = role_permissions;
    }
    //</editor-fold>
}
