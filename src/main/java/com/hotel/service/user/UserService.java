package com.hotel.service.user;

import com.hotel.database.item.CartDatabase;
import com.hotel.database.room.RoomDatabase;
import com.hotel.model.item.Cart;
import com.hotel.model.room.Room;
import com.hotel.model.user.ApplicationUser;
import com.hotel.database.user.UserDatabase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private UserDatabase database;

    private CartDatabase cartDatabase;

    private RoomDatabase roomDatabase;

    public UserService(UserDatabase database, CartDatabase cartDatabase, RoomDatabase roomDatabase) {
        this.database = database;
        this.cartDatabase = cartDatabase;
        this.roomDatabase = roomDatabase;
    }

    public void add(ApplicationUser user) {
        database.addUser(user);
    }

    public ApplicationUser find(long id) {
        return database.find(id);
    }

    public ApplicationUser find(String username) {
        return database.find(username);
    }

    public boolean exists(long id) {
        return database.containsID(id);
    }

    public boolean exists(String username) {
        return database.containsUser(username);
    }

    public ApplicationUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return find(username);
    }

    public Room getRoom(ApplicationUser user) {
        return roomDatabase.find(user);
    }

    public Cart getActiveCart(ApplicationUser user) {
        return user.getActiveCart();
    }

    public void createEmptyCart(ApplicationUser user) {
        Cart c = new Cart(false, user, new HashSet<>());
        cartDatabase.add(c);
        user.getUser_carts().add(c);
        database.addUser(user);
    }

    public void sendActiveCart(ApplicationUser user) {
        Cart c = getActiveCart(user);
        c.setCompleted(true);
        cartDatabase.add(c);
        createEmptyCart(user);
    }

    public void clearActiveCart(ApplicationUser user) {
        Cart c = getActiveCart(user);
        c.clear();
        cartDatabase.add(c);
    }

    public void removeCart(ApplicationUser user, Cart c) {
        user.getUser_carts().remove(c);
        cartDatabase.remove(c);
        if(!user.hasActiveCart()) createEmptyCart(user);
        else database.addUser(user);
    }
}
