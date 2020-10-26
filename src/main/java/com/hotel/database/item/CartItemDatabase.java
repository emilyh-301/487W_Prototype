package com.hotel.database.item;

import com.hotel.database.jpa.JpaCartItemRepository;
import com.hotel.database.jpa.JpaMenuItemRepository;
import com.hotel.model.item.CartItem;
import com.hotel.model.item.MenuItem;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

@Repository
public class CartItemDatabase {

    private final JpaCartItemRepository repo;

    private final JpaMenuItemRepository menu_repo;

    public CartItemDatabase(JpaCartItemRepository repo, JpaMenuItemRepository menu_repo) {
        this.repo = repo;
        this.menu_repo = menu_repo;
    }

    public Collection<CartItem> getDatabase(Sort s) {
        return repo.getAll(s);
    }

    public void add(CartItem cartItem) {
        if(cartItem != null) repo.save(cartItem);
    }

    public void edit(@NotNull long id, long new_item_id, int new_quantity, String new_notes) throws Exception {

        CartItem item = find(id);

        if(item == null) return;

        Optional<MenuItem> menuItem = menu_repo.findById(new_item_id);

        if(menuItem.isPresent()) item.setItem(menuItem.get());
        else throw new Exception("Given menu item id is invalid.");

        if(new_quantity >= 1) item.setQuantity(new_quantity);
        else item.setQuantity(1);

        item.setNotes(new_notes == null? "" : new_notes);

        repo.save(item);

    }

    public void remove(CartItem cartItem) {
        repo.delete(cartItem);
    }

    public void remove(long id) {
        repo.deleteById(id);
    }

    public CartItem find(long id) {
        Optional<CartItem> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    public boolean contains(long id) {
        return repo.existsById(id);
    }
}
