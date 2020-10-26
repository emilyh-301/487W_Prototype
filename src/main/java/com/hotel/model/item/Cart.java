package com.hotel.model.item;

import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;

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
    private Long id;

    @Column(name = "completed")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ApplicationUser user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    public Cart() {

    }

    public Cart(long id, boolean completed, ApplicationUser user, Set<CartItem> items) {
        this.id = id;
        this.completed = completed;
        this.user = user;
        this.items = items == null? new HashSet<>() : items;
    }

    public Cart(boolean completed, ApplicationUser user, Set<CartItem> items) {
        this.completed = completed;
        this.user = user;
        this.items = items == null? new HashSet<>() : items;
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
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //<editor-fold desc="Getters and Setters">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
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
