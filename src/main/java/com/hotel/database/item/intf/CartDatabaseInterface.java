package com.hotel.database.item.intf;

import com.hotel.model.item.Cart;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public interface CartDatabaseInterface {

    Collection<Cart> getDatabase(Sort s);

    void add(Cart cart);

    void edit(@NotNull long id, boolean new_completed, int new_room, Set<Long> new_item_ids);

    void remove(Cart cart);

    void remove(long id);

    Cart find(long id);

    boolean contains(long id);

}
