package com.hotel.model.repo.item;

import com.hotel.jpa.JpaCartItemRepository;
import com.hotel.jpa.JpaMenuItemRepository;
import com.hotel.model.item.CartItem;
import com.hotel.model.item.MenuItem;
import com.hotel.model.repo.item.intf.CartItemDatabaseInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public class CartItemDatabase implements CartItemDatabaseInterface {

    private final JpaCartItemRepository repo;

    private final JpaMenuItemRepository menu_repo;

    public CartItemDatabase(JpaCartItemRepository repo, JpaMenuItemRepository menu_repo) {
        this.repo = repo;
        this.menu_repo = menu_repo;
    }

    @Override
    public Collection<CartItem> getDatabase(Sort s) {
        return repo.getAll(s);
    }

    @Override
    public void add(CartItem cartItem) {
        if(cartItem != null && !contains(cartItem.getId())) repo.save(cartItem);
    }

    @Override
    public void edit(@NotNull int id, int new_item_id, int new_quantity, String new_notes) throws Exception {

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

    @Override
    public void remove(CartItem cartItem) {
        repo.delete(cartItem);
    }

    @Override
    public void remove(int id) {
        repo.deleteById(id);
    }

    @Override
    public CartItem find(int id) {
        Optional<CartItem> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    @Override
    public boolean contains(int id) {
        return repo.existsById(id);
    }
}
