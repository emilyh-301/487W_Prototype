package com.hotel.model.item;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_SEQ")
    @SequenceGenerator(name = "ORDER_ID_SEQ", sequenceName = "ORDER_ID_SEQ", allocationSize = 250)
    @Column(name = "order_id")
    private int id;

    /*
    @Column(name = "cart_id")
    private int cart_id;
     */

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "timeOfRequest")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;

    public Order() {

    }

    public Order(int id, Cart cart, Status status, Date time) {
        this.id = id;
        this.cart = cart;
        this.status = status;
        this.time = time;
    }

    public Order(int id, Cart cart, Status status, long time) {
        this.id = id;
        this.cart = cart;
        this.status = status;
        this.time = new Date(time);
    }

    //<editor-fold desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = new Date(time);
    }

    //</editor-fold>

    public enum Status {
        RECIEVED, IN_PROGRESS, COMPLETED;
    }
}
