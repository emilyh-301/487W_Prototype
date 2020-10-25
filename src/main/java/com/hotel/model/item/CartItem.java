package com.hotel.model.item;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_ITEM_ID_SEQ")
    @SequenceGenerator(name = "CART_ITEM_ID_SEQ", sequenceName = "CART_ITEM_ID_SEQ", allocationSize = 250)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public CartItem() {

    }

    public CartItem(int id, MenuItem item, int quantity, String notes, Cart cart) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.notes = notes;
        this.cart = cart;
    }

    public void incrementQuantityBy(int i) {
        quantity += i;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantityBy(int i) {
        quantity -= i;
    }

    public void decrementQuantity() {
        quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id;
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

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    //</editor-fold>
}
