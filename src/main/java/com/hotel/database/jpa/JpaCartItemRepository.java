package com.hotel.database.jpa;

import com.hotel.model.item.CartItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JpaCartItemRepository extends CrudRepository<CartItem, Long> {

    @Query(value = "select c from CartItem c")
    Collection<CartItem> getAll(Sort sort);

    @Query(value = "select c from CartItem c where " +
            "(:item_id is null or c.item.id = :item_id) and " +
            "(:quantity is null or c.quantity = :quantity) and " +
            "(:notes is null or c.notes = :notes) and " +
            "(:cart_id is null or c.cart.id = :cart_id)")
    Collection<CartItem> getAll(@Param("item_id") Long item_id,
                                @Param("quantity") Integer quantity,
                                @Param("notes") String notes,
                                @Param("cart_id") Integer cart_id,
                                Sort sort);

    @Query(value = "select c from CartItem c where c.quantity > :quantity")
    Collection<CartItem> getAllHigherThanQuantity(@Param("quantity") int quantity, Sort s);

    @Query(value = "select c from CartItem c where c.quantity < :quantity")
    Collection<CartItem> getAllLessThanQuantity(@Param("quantity") int quantity, Sort s);

    @Query(value = "select c from CartItem c where c.quantity > :quantity_min and c.quantity < :quantity_max")
    Collection<CartItem> getAllBetweenQuantities(@Param("quantity_min") int quantity_min, @Param("quantity_max") int quantity_max, Sort s);

    @Query(value = "select c from CartItem c where c.item.id = :ItemID")
    Collection<CartItem> selectMenuItemsFromCart(@Param("ItemID") long itemId);

    @Query(value = "select c.cart.id from CartItem c where c.item.id = :ItemID")
    Collection<Long> selectCartIdsFromCartItems(@Param("ItemID") long itemId);

}
