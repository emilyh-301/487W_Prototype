package com.hotel.service.item.intf;

import com.hotel.model.item.Order;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public interface OrderServiceInterface {

    ArrayList<Order> getOrders();

    void add(Order order);

    Order find(int id);

    void edit(@NotNull int id, int new_cart_id, String new_status, long new_time) throws Exception;

    void remove(Order order);

    void remove(int id);

    boolean exists(int id);

}
