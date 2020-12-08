package com.hotel.service.item;

import com.hotel.database.item.CartItemDatabase;
import com.hotel.model.item.Cart;
import com.hotel.database.item.CartDatabase;
import com.hotel.model.item.CartItem;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Service
public class CartService {

    private final CartDatabase database;
    private final CartItemDatabase cartItemDatabase;

    public CartService(CartDatabase database, CartItemDatabase cartItemDatabase) {
        this.database = database;
        this.cartItemDatabase = cartItemDatabase;
    }

    public Collection<Cart> getCarts(Sort s) {
        return database.getDatabase(s);
    }

    public void add(Cart cart) {
        database.add(cart);
    }

    public void addToCart(Cart cart, CartItem cartItem){

        cartItem.setCart(cart);

        cart.addItem(cartItem);
        // update cart
        database.add(cart);
    }

    public void removeFromCart(Cart cart, Long cartItem){

        //cartItemDatabase.remove(cartItem);
        cart.removeItem(cartItemDatabase.find(cartItem));
        // update cart
        database.add(cart);
    }

    public void removeFromCart(Cart cart, CartItem cartItem){

        //cartItemDatabase.remove(cartItem);
        cart.removeItem(cartItem);
        // update cart
        database.add(cart);
    }

    public void clearCart(Cart cart) {
        cart.clear();
        database.add(cart);
    }

    public Cart find(long id) {
        return database.find(id);
    }

    public void edit(@NotNull long id, boolean new_completed, Set<Long> new_item_ids) {
        database.edit(id, new_completed, new_item_ids);
    }

    public void setCompleted(@NotNull long id, boolean completed) {
        database.setComplete(id, completed);
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
