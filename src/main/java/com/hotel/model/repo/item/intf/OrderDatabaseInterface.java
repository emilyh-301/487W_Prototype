package com.hotel.model.repo.item.intf;

import com.hotel.model.item.Order;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public interface OrderDatabaseInterface {

    Collection<Order> getDatabase(Sort s);

    void add(Order order);

    void edit(@NotNull int id, int new_cart_id, String new_status, long new_time) throws Exception;

    void remove(Order order);

    void remove(int id);

    Order find(int id);

    boolean contains(int id);

}
