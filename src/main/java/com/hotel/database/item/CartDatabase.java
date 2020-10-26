package com.hotel.database.item;

import com.hotel.database.jpa.JpaCartItemRepository;
import com.hotel.database.jpa.JpaCartRepository;
import com.hotel.model.item.Cart;
import com.hotel.model.item.CartItem;
import com.hotel.database.item.intf.CartDatabaseInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class CartDatabase implements CartDatabaseInterface {

    private final JpaCartRepository repo;

    private final JpaCartItemRepository cart_item_repo;

    public CartDatabase(JpaCartRepository repo, JpaCartItemRepository cart_item_repo) {
        this.repo = repo;
        this.cart_item_repo = cart_item_repo;
    }

    @Override
    public Collection<Cart> getDatabase(Sort sort) {
        return repo.getAll(sort);
    }

    @Override
    public void add(Cart cart) {
        if(cart != null) repo.save(cart);
    }

    @Override
    public void edit(@NotNull int id, boolean new_completed, int new_room, Set<Integer> new_item_ids) {

        Cart cart = find(id);

        if(cart == null) return;

        cart.setCompleted(new_completed);

        cart.setRoom(new_room);

        cart.setItems(new HashSet<>());

        for(Integer i : new_item_ids) {
            Optional<CartItem> cartItem = cart_item_repo.findById(i);
            if(cartItem.isPresent()) cart.getItems().add(cartItem.get());
        }

        repo.save(cart);
    }

    @Override
    public void remove(Cart cart) {
        repo.delete(cart);
    }

    @Override
    public void remove(int id) {
        repo.deleteById(id);
    }

    @Override
    public Cart find(int id) {
        Optional<Cart> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    @Override
    public boolean contains(int id) {
        return repo.existsById(id);
    }
}
