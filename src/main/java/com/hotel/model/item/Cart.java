package com.hotel.model.item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_ID_SEQ")
    @SequenceGenerator(name = "CART_ID_SEQ", sequenceName = "CART_ID_SEQ", allocationSize = 250)
    @Column(name = "cart_id")
    private int id;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "room")
    private int room;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CartItem> items;

    public Cart() {

    }

    public Cart(int id, boolean completed, int room, Collection<CartItem> items) {
        this.id = id;
        this.completed = completed;
        this.room = room;
        this.items = items;
    }

    //<editor-fold desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Collection<CartItem> getItems() {
        return items;
    }

    public void setItems(Collection<CartItem> items) {
        this.items = items;
    }

    //</editor-fold>
}
