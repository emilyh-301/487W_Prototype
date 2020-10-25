package com.hotel.database.item.intf;

import com.hotel.model.item.CartItem;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface CartItemDatabaseInterface {

    Collection<CartItem> getDatabase(Sort s);

    void add(CartItem item);

    void edit(@NotNull int id, int new_item_id, int new_quantity, String new_notes) throws Exception;

    void remove(CartItem item);

    void remove(int id);

    CartItem find(int id);

    boolean contains(int id);

}
