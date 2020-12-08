package com.hotel.service.item;

import com.hotel.model.item.Order;
import com.hotel.database.item.OrderDatabase;
import com.hotel.model.request.Request;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import javax.persistence.*;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class OrderService {

    private final OrderDatabase database;

    public OrderService(OrderDatabase database) {
        this.database = database;
    }

    public Collection<Order> getOrders(Sort s) {
        return database.getDatabase(s);
    }

    public void add(Order order) {
        database.add(order);
    }

    public Order find(long id) {
        return database.find(id);
    }

    public void edit(@NotNull long id, long new_cart_id, String new_status, long new_time) throws Exception {
        database.edit(id, new_cart_id, new_status, new_time);
    }

    public void remove(Order order) {
        database.remove(order);
    }

    public void remove(long id) {
        database.remove(id);
    }

    public boolean exists(long id) {
        return database.contains(id);
    }

    public Collection<Order> getDatabase(Sort sort) {
        return database.getDatabase(sort);
    }
}
