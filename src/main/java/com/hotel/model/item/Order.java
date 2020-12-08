package com.hotel.model.item;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_SEQ")
    @SequenceGenerator(name = "ORDER_ID_SEQ", sequenceName = "ORDER_ID_SEQ", allocationSize = 250)
    @Column(name = "order_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "timeOfRequest")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;

    @JoinColumn(name = "notes")
    private String notes;

    /**
     * The room number that made the request
     */
    @Column(name = "roomNumber")
    protected int room;

    public Order() {

    }

    public Order(long id, Cart cart, Status status, Date time, String notes, int room) {
        this.id = id;
        this.cart = cart;
        this.status = status;
        this.time = time;
        this.notes = notes;
        this.room = room;
    }

    public Order(long id, Cart cart, Status status, long time, String notes, int room) {
        this.id = id;
        this.cart = cart;
        this.status = status;
        this.time = new Date(time);
        this.notes = notes;
        this.room = room;
    }

    public Order(Cart cart, Status status, Date time, String notes, int room) {
        this.cart = cart;
        this.status = status;
        this.time = time;
        this.notes = notes;
        this.room = room;
    }

    public Order(Cart cart, Status status, long time, String notes, int room) {
        this.cart = cart;
        this.status = status;
        this.time = new Date(time);
        this.notes = notes;
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id.equals(order.id);
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

    public String getFormattedTime() {
        return DateFormat.getDateTimeInstance().format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = new Date(time);
    }



    //</editor-fold>

    public enum Status {
        RECEIVED, IN_PROGRESS, COMPLETED;
    }

//        public  boolean Status {
//        RECIEVED, IN_PROGRESS, COMPLETED;
//    }

//    public String getStatusString()
//    {
//        if(status) return "IN_PROGRESS";
//        return "COMPLETED";
//    }
}
