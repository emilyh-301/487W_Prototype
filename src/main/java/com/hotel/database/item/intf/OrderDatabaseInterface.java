package com.hotel.database.item.intf;

import com.hotel.model.item.Order;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface OrderDatabaseInterface {

    Collection<Order> getDatabase(Sort s);

    void add(Order order);

    void edit(@NotNull long id, long new_cart_id, String new_status, long new_time) throws Exception;

    void remove(Order order);

    void remove(long id);

    Order find(long id);

    boolean contains(long id);

}
