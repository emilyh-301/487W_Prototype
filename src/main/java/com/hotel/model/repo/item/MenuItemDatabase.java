package com.hotel.model.repo.item;

import com.hotel.jpa.JpaMenuItemRepository;
import com.hotel.model.MenuItem;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MenuItemDatabase implements MenuItemDatabaseInterface {

    private final JpaMenuItemRepository repo;

    public MenuItemDatabase(JpaMenuItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public ArrayList<MenuItem> getDatabase() {
        return (ArrayList<MenuItem>) repo.findAll();
    }

    @Override
    public void add(MenuItem item) {
        if(item != null && !containsId(item.getId())) repo.save(item);
    }

    @Override
    public void remove(MenuItem item) {
        repo.delete(item);
    }

    @Override
    public void remove(int id) {
        repo.deleteById(id);
    }

    @Override
    public void edit(@NotNull int id, String new_name, String new_allergens, double new_price, String new_description) {

        MenuItem item = find(id);

        if(item == null) return;

        item.setName((new_name == null || new_name.isEmpty())? MenuItem.DEFAULT_NAME : new_name);
        item.setAllergens(new_allergens == null? "" : new_name);
        item.setPrice(new_price < MenuItem.MINIMUM_PRICE? new BigDecimal(MenuItem.MINIMUM_PRICE) : new BigDecimal(new_price));
        item.setDescription((new_description == null || new_description.isEmpty())? MenuItem.DEFAULT_DESCRIPTION : new_description);

        repo.save(item);

    }

    @Override
    public MenuItem find(int id) {

        Optional<MenuItem> item = repo.findById(id);

        return item.isPresent()? item.get() : null;

    }

    @Override
    public ArrayList<MenuItem> findName(String name) {

        ArrayList<MenuItem> items = new ArrayList<>();

        repo.findAll().forEach(i -> {
            if(i.getName().equals(name) || i.getName().contains(name)) items.add(i);
        });

        return items;

    }

    @Override
    public boolean containsId(int id) {
        return repo.existsById(id);
    }
}
