package com.hotel.model.user;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_ID_SEQ")
    @SequenceGenerator(name = "PERMISSION_ID_SEQ", sequenceName = "PERMISSION_ID_SEQ", allocationSize = 250)
    @Column(name = "permission_id")
    private Long permission_id;

    @Column(name = "permission_name")
    private String permission_name;

    public Permissions() {

    }

    public Permissions(Long permission_id, String permission_name) {
        this.permission_id = permission_id;
        this.permission_name = permission_name;
    }

    public Permissions(String permission_name) {
        this.permission_name = permission_name;
    }

    //<editor-fold desc="Getters and Setters">
    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }
    //</editor-fold>
}
