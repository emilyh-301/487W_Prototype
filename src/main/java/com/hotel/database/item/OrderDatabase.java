package com.hotel.database.item;

import com.hotel.database.jpa.JpaCartRepository;
import com.hotel.database.jpa.JpaOrderRepository;
import com.hotel.model.item.Cart;
import com.hotel.model.item.Order;
import com.hotel.database.item.intf.OrderDatabaseInterface;
import com.hotel.model.request.AbstractRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

@Repository
public class OrderDatabase implements OrderDatabaseInterface {

    private final JpaOrderRepository repo;

    private final JpaCartRepository cart_repo;

    public OrderDatabase(JpaOrderRepository repo, JpaCartRepository cart_repo) {
        this.repo = repo;
        this.cart_repo = cart_repo;
    }

    @Override
    public Collection<Order> getDatabase(Sort s) {
        return repo.getAll(s);
    }

    @Override
    public void add(Order order) {
        if(order != null) repo.save(order);
    }

    @Override
    public void edit(@NotNull long id, long new_cart_id, String new_status, long new_time) throws Exception {
        
        Order order = find(id);

        if(order == null) return;

        Optional<Cart> cart = cart_repo.findById(new_cart_id);

        if(cart.isPresent()) order.setCart(cart.get());
        else throw new Exception("Cart id does not correspond to a valid cart.");

        try {
            order.setStatus(Order.Status.valueOf(new_status));
        } catch (IllegalArgumentException e) {
            order.setStatus(Order.Status.RECIEVED);
        }

        if(new_time < AbstractRequest.MINIMUM_TIME) order.setTime(AbstractRequest.MINIMUM_TIME);
        else order.setTime(new_time);

        repo.save(order);

    }

    @Override
    public void remove(Order order) {
        repo.delete(order);
    }

    @Override
    public void remove(long id) {
        repo.deleteById(id);
    }

    @Override
    public Order find(long id) {
        Optional<Order> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    @Override
    public boolean contains(long id) {
        return repo.existsById(id);
    }
}
