package com.hotel.service.item;

import com.hotel.model.item.CartItem;
import com.hotel.database.item.CartItemDatabase;
import com.hotel.service.item.intf.CartItemServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
public class CartItemService implements CartItemServiceInterface {

    private final CartItemDatabase database;

    public CartItemService(CartItemDatabase database) {
        this.database = database;
    }

    @Override
    public Collection<CartItem> getItems(Sort s) {
        return database.getDatabase(s);
    }

    @Override
    public void add(CartItem item) {
        database.add(item);
    }

    @Override
    public CartItem find(int id) {
        return database.find(id);
    }

    @Override
    public void edit(@NotNull int id, int new_item_id, int new_quantity, String new_notes) throws Exception {
        database.edit(id, new_item_id, new_quantity, new_notes);
    }

    @Override
    public void remove(CartItem item) {
        database.remove(item);
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
