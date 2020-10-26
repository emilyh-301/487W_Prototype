package com.hotel.service.item;

import com.hotel.model.item.Cart;
import com.hotel.database.item.CartDatabase;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Service
public class CartService {

    private final CartDatabase database;

    public CartService(CartDatabase database) {
        this.database = database;
    }

    public Collection<Cart> getCarts(Sort s) {
        return database.getDatabase(s);
    }

    public void add(Cart cart) {
        database.add(cart);
    }

    public Cart find(long id) {
        return database.find(id);
    }

    public void edit(@NotNull long id, boolean new_completed, Set<Long> new_item_ids) {
        database.edit(id, new_completed, new_item_ids);
    }

    public void remove(Cart cart) {
        database.remove(cart);
    }

    public void remove(long id) {
        database.remove(id);
    }

    public boolean exists(long id) {
        return database.contains(id);
    }
}
