package com.hotel.database.item.intf;

import com.hotel.model.item.CartItem;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface CartItemDatabaseInterface {

    Collection<CartItem> getDatabase(Sort s);

    void add(CartItem item);

    void edit(@NotNull long id, long new_item_id, int new_quantity, String new_notes) throws Exception;

    void remove(CartItem item);

    void remove(long id);

    CartItem find(long id);

    boolean contains(long id);

}
