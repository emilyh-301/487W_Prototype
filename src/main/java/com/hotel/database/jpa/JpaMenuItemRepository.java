package com.hotel.database.jpa;

import com.hotel.model.item.Allergen;
import com.hotel.model.item.MenuItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * CRUD (Create, Read, Update, Delete) repository for menu items
 */
public interface JpaMenuItemRepository extends CrudRepository<MenuItem, Integer> {

    @Query(value = "select i from MenuItem i")
    Collection<MenuItem> getAll(Sort sort);

    @Query(value = "select i from MenuItem i where " +
            "(:id is null or i.id = :id) and " +
            "(:name is null or i.name = :name) and " +
            "(:price is null or i.price = :price) and " +
            "(:desc is null or i.description = :desc) and " +
            "(:image is null or i.image = :image) and " +
            "(:allergen is null or :allergen in (i.allergens))")
    Collection<MenuItem> getAll(@Param("id") Integer item_id,
                                @Param("name") String name,
                                @Param("price") Double price,
                                @Param("desc") String description,
                                @Param("image") String image,
                                @Param("allergen") Allergen allergen,
                                Sort sort);

    @Query(value = "select i from MenuItem i where i.price < :price")
    Collection<MenuItem> getAllLessThanPrice(@Param("price") Double price, Sort sort);

    @Query(value = "select i from MenuItem i where i.price > :price")
    Collection<MenuItem> getAllGreaterThanPrice(@Param("price") Double price, Sort sort);

    @Query(value = "select i from MenuItem i where i.price between :price_min and :price_max")
    Collection<MenuItem> getAllInPriceRange(@Param("price_min") Double price_min, @Param("price_max") Double price_max, Sort sort);
}
