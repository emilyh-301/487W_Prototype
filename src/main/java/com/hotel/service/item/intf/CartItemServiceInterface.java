package com.hotel.service.item.intf;

import com.hotel.model.item.CartItem;
import com.hotel.model.item.MenuItem;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public interface CartItemServiceInterface {

    ArrayList<CartItem> getItems();

    void add(CartItem item);

    CartItem find(int id);

    void edit(@NotNull int id, int new_item_id, int new_quantity, String new_notes) throws Exception;

    void remove(CartItem item);

    void remove(int id);

    boolean exists(int id);

}
