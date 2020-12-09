package com.hotel.service.item;

import com.hotel.database.item.AllergenDatabase;
import com.hotel.model.item.Allergen;
import com.hotel.model.item.MenuItem;
import com.hotel.database.item.MenuItemDatabase;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MenuItemService {

    private final MenuItemDatabase database;

    private final AllergenDatabase allergenDatabase;

    public MenuItemService(MenuItemDatabase database, AllergenDatabase allergenDatabase) {
        this.database = database;
        this.allergenDatabase = allergenDatabase;
    }

    public Collection<MenuItem> getItems(Sort s) {
        return database.getDatabase(s);
    }

    public void add(MenuItem item) {

        for(Allergen a : item.getAllergens())
            if(a != null && !allergenDatabase.contains(a.getAllergen())) allergenDatabase.add(a);

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

    public void edit(@NotNull long id, String new_name, Set<String> new_allergens, double new_price, String new_description, String imageName) {
        database.edit(id, new_name, new_allergens, new_price, new_description, imageName);
    }

    public void edit(@NotNull long id, String new_name, HashSet<String> new_allergens, double new_price, String new_description) {
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
