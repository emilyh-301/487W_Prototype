package com.hotel.service.item;

import com.hotel.model.item.MenuItem;
import com.hotel.database.item.MenuItemDatabase;
import com.hotel.service.item.intf.MenuItemServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public class MenuItemService implements MenuItemServiceInterface {

    private final MenuItemDatabase database;

    public MenuItemService(MenuItemDatabase database) {
        this.database = database;
    }

    @Override
    public Collection<MenuItem> getItems(Sort s) {
        return database.getDatabase(s);
    }

    @Override
    public void add(MenuItem item) {
        database.add(item);
    }

    @Override
    public MenuItem find(long id) {
        return database.find(id);
    }

    @Override
    public ArrayList<MenuItem> findName(String name) {
        return database.findName(name);
    }

    @Override
    public void edit(@NotNull long id, String new_name, Set<String> new_allergens, double new_price, String new_description) {
        database.edit(id, new_name, new_allergens, new_price, new_description);
    }

    @Override
    public void remove(MenuItem item) {
        database.remove(item);
    }

    @Override
    public void remove(long id) {
        database.remove(id);
    }

    @Override
    public boolean exists(long id) {
        return database.containsId(id);
    }
}
