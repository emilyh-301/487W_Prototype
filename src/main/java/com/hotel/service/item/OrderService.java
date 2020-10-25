package com.hotel.service.item;

import com.hotel.model.item.CartItem;
import com.hotel.model.item.Order;
import com.hotel.model.repo.item.OrderDatabase;
import com.hotel.service.item.intf.OrderServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderDatabase database;

    public OrderService(OrderDatabase database) {
        this.database = database;
    }

    @Override
    public Collection<Order> getOrders(Sort s) {
        return database.getDatabase(s);
    }

    @Override
    public void add(Order order) {
        database.add(order);
    }

    @Override
    public Order find(int id) {
        return database.find(id);
    }

    @Override
    public void edit(@NotNull int id, int new_cart_id, String new_status, long new_time) throws Exception {
        database.edit(id, new_cart_id, new_status, new_time);
    }

    @Override
    public void remove(Order order) {
        database.remove(order);
    }

    @Override
    public void remove(int id) {
        database.remove(id);
    }

    @Override
    public boolean exists(int id) {
        return database.contains(id);
    }
}
