package com.hotel.database.item.intf;

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

    void remove(long id);

    void edit(@NotNull long id, String new_name, Set<String> new_allergens, double new_price, String new_description);

    MenuItem find(long id);

    ArrayList<MenuItem> findName(String name);

    boolean containsId(long id);

}
