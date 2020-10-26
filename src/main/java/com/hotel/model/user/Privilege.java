package com.hotel.model.user;

import javax.persistence.*;

@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIV_ID_SEQ")
    @SequenceGenerator(name = "PRIV_ID_SEQ", sequenceName = "PRIV_ID_SEQ", allocationSize = 250)
    private Long privilege_id;

    private String name;

    public Privilege() {

    }

    public Privilege(Long privilege_id, String name) {
        this.privilege_id = privilege_id;
        this.name = name;
    }

    public Privilege(String name) {
        this.name = name;
    }

    //<editor-fold desc="Getters and Setters">
    public Long getPrivilege_id() {
        return privilege_id;
    }

    public void setPrivilege_id(Long id) {
        this.privilege_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
