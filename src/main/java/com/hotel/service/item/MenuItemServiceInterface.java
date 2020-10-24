package com.hotel.service.item;

import com.hotel.model.MenuItem;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

public interface MenuItemServiceInterface {

    ArrayList<MenuItem> getItems();

    void add(MenuItem item);

    MenuItem find(int id);

    ArrayList<MenuItem> findName(String name);

    void edit(@NotNull int id, String new_name, Set<String> new_allergens, double new_price, String new_description);

    void remove(MenuItem item);

    void remove(int id);

    boolean exists(int id);
}
