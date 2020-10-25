package com.hotel.service.item.intf;

import com.hotel.model.item.Cart;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

public interface CartServiceInterface {

    ArrayList<Cart> getCarts();

    void add(Cart cart);

    Cart find(int id);

    void edit(@NotNull int id, boolean new_completed, int new_room, Set<Integer> new_item_ids);

    void remove(Cart cart);

    void remove(int id);

    boolean exists(int id);

}
