package com.hotel.model.repo.item.intf;

import com.hotel.model.item.Cart;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public interface CartDatabaseInterface {

    Collection<Cart> getDatabase(Sort s);

    void add(Cart cart);

    void edit(@NotNull int id, boolean new_completed, int new_room, Set<Integer> new_item_ids);

    void remove(Cart cart);

    void remove(int id);

    Cart find(int id);

    boolean contains(int id);

}
