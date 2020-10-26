package com.hotel.database.item;

import com.hotel.database.jpa.JpaCartItemRepository;
import com.hotel.database.jpa.JpaCartRepository;
import com.hotel.database.room.RoomDatabase;
import com.hotel.model.item.Cart;
import com.hotel.model.item.CartItem;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class CartDatabase {

    private final JpaCartRepository repo;

    private final JpaCartItemRepository cart_item_repo;

    public CartDatabase(JpaCartRepository repo, JpaCartItemRepository cart_item_repo) {
        this.repo = repo;
        this.cart_item_repo = cart_item_repo;
    }

    public Collection<Cart> getDatabase(Sort sort) {
        return repo.getAll(sort);
    }

    public void add(Cart cart) {
        if(cart != null) repo.save(cart);
    }

    public void edit(@NotNull long id, boolean new_completed, Set<Long> new_item_ids) {

        Cart cart = find(id);

        if(cart == null) return;

        cart.setCompleted(new_completed);

        cart.setItems(new HashSet<>());

        for(Long i : new_item_ids) {
            Optional<CartItem> cartItem = cart_item_repo.findById(i);
            if(cartItem.isPresent()) cart.getItems().add(cartItem.get());
        }

        repo.save(cart);
    }

    public void remove(Cart cart) {
        repo.delete(cart);
    }

    public void remove(long id) {
        repo.deleteById(id);
    }

    public Cart find(long id) {
        Optional<Cart> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    public boolean contains(long id) {
        return repo.existsById(id);
    }
}
