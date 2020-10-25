package com.hotel.model.repo.item.intf;

import com.hotel.model.item.MenuItem;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public interface MenuItemDatabaseInterface {

    Collection<MenuItem> getDatabase(Sort s);

    void add(MenuItem item);

    void remove(MenuItem item);

    void remove(int id);

    void edit(@NotNull int id, String new_name, Set<String> new_allergens, double new_price, String new_description);

    MenuItem find(int id);

    ArrayList<MenuItem> findName(String name);

    boolean containsId(int id);

}
