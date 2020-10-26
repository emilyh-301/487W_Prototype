package com.hotel.service.item;

import com.hotel.model.item.MenuItem;
import com.hotel.database.item.MenuItemDatabase;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public class MenuItemService {

    private final MenuItemDatabase database;

    public MenuItemService(MenuItemDatabase database) {
        this.database = database;
    }

    public Collection<MenuItem> getItems(Sort s) {
        return database.getDatabase(s);
    }

    public void add(MenuItem item) {
        database.add(item);
    }

    public MenuItem find(long id) {
        return database.find(id);
    }

    public ArrayList<MenuItem> findName(String name) {
        return database.findName(name);
    }

    public void edit(@NotNull long id, String new_name, Set<String> new_allergens, double new_price, String new_description) {
        database.edit(id, new_name, new_allergens, new_price, new_description);
    }

    public void remove(MenuItem item) {
        database.remove(item);
    }

    public void remove(long id) {
        database.remove(id);
    }

    public boolean exists(long id) {
        return database.containsId(id);
    }
}
