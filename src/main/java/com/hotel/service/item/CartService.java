package com.hotel.service.item;

import com.hotel.database.item.CartItemDatabase;
import com.hotel.model.item.Cart;
import com.hotel.database.item.CartDatabase;
import com.hotel.model.item.CartItem;
import com.hotel.service.item.intf.CartServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Service
public class CartService implements CartServiceInterface {

    private final CartDatabase database;

    public CartService(CartDatabase database) {
        this.database = database;
    }

    @Override
    public Collection<Cart> getCarts(Sort s) {
        return database.getDatabase(s);
    }

    @Override
    public void add(Cart cart) {
        database.add(cart);
    }

    @Override
    public Cart find(int id) {
        return database.find(id);
    }

    @Override
    public void edit(@NotNull int id, boolean new_completed, int new_room, Set<Integer> new_item_ids) {
        database.edit(id, new_completed, new_room, new_item_ids);
    }

    @Override
    public void remove(Cart cart) {
        database.remove(cart);
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
