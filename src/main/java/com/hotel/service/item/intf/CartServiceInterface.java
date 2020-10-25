package com.hotel.service.item.intf;

import com.hotel.model.item.Cart;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public interface CartServiceInterface {

    Collection<Cart> getCarts(Sort s);

    void add(Cart cart);

    Cart find(int id);

    void edit(@NotNull int id, boolean new_completed, int new_room, Set<Integer> new_item_ids);

    void remove(Cart cart);

    void remove(int id);

    boolean exists(int id);

}
