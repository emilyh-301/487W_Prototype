package com.hotel.model.item;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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
    private Set<CartItem> items;

    public Cart() {

    }

    public Cart(int id, boolean completed, int room, Set<CartItem> items) {
        this.id = id;
        this.completed = completed;
        this.room = room;
        this.items = items;
    }

    public Cart(boolean completed, int room, Set<CartItem> items) {
        this.completed = completed;
        this.room = room;
        this.items = items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public void setItems(Collection<CartItem> items) {
        this.items = new HashSet<>(items);
    }

    //</editor-fold>

}
