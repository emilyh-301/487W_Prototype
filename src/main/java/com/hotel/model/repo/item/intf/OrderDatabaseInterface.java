package com.hotel.model.repo.item.intf;

import com.hotel.model.item.Order;

import javax.validation.constraints.NotNull;
import java.util.Set;

public interface OrderDatabaseInterface {

    Set<Order> getDatabase();

    void add(Order order);

    void edit(@NotNull int id, int new_cart_id, String new_status, long new_time) throws Exception;

    void remove(Order order);

    void remove(int id);

    Order find(int id);

    boolean contains(int id);

}
