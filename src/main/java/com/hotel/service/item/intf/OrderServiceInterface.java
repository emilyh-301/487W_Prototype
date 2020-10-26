package com.hotel.service.item.intf;

import com.hotel.model.item.Order;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

public interface OrderServiceInterface {

    Collection<Order> getOrders(Sort s);

    void add(Order order);

    Order find(long id);

    void edit(@NotNull long id, long new_cart_id, String new_status, long new_time) throws Exception;

    void remove(Order order);

    void remove(long id);

    boolean exists(long id);

}
