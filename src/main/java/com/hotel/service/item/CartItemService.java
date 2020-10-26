package com.hotel.service.item;

import com.hotel.model.item.CartItem;
import com.hotel.database.item.CartItemDatabase;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
public class CartItemService {

    private final CartItemDatabase database;

    public CartItemService(CartItemDatabase database) {
        this.database = database;
    }

    public Collection<CartItem> getItems(Sort s) {
        return database.getDatabase(s);
    }

    public void add(CartItem item) {
        database.add(item);
    }

    public CartItem find(long id) {
        return database.find(id);
    }

    public void edit(@NotNull long id, long new_item_id, int new_quantity, String new_notes) throws Exception {
        database.edit(id, new_item_id, new_quantity, new_notes);
    }

    public void remove(CartItem item) {
        database.remove(item);
    }

    public void remove(long id) {
        database.remove(id);
    }

    public boolean exists(long id) {
        return database.contains(id);
    }
}
