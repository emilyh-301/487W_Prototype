package com.hotel.model.repo.item;

import com.hotel.jpa.JpaCartRepository;
import com.hotel.jpa.JpaOrderRepository;
import com.hotel.model.item.Cart;
import com.hotel.model.item.Order;
import com.hotel.model.repo.item.intf.OrderDatabaseInterface;
import com.hotel.model.request.AbstractRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
        if(order != null && !contains(order.getId())) repo.save(order);
    }

    @Override
    public void edit(@NotNull int id, int new_cart_id, String new_status, long new_time) throws Exception {
        
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
    public void remove(int id) {
        repo.deleteById(id);
    }

    @Override
    public Order find(int id) {
        Optional<Order> a = repo.findById(id);

        return a.isPresent()? a.get() : null;
    }

    @Override
    public boolean contains(int id) {
        return repo.existsById(id);
    }
}
