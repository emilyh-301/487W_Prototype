package com.hotel.service.item.intf;

import com.hotel.model.item.MenuItem;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public interface MenuItemServiceInterface {

    Collection<MenuItem> getItems(Sort s);

    void add(MenuItem item);

    MenuItem find(int id);

    ArrayList<MenuItem> findName(String name);

    void edit(@NotNull int id, String new_name, Set<String> new_allergens, double new_price, String new_description);

    void remove(MenuItem item);

    void remove(int id);

    boolean exists(int id);
}
