package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.model.item.CartItem;
import com.hotel.model.item.MenuItem;
import com.hotel.service.item.CartService;
import com.hotel.service.item.MenuItemService;
import com.hotel.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/menu")
public class MenuPageController {
    private final MenuItemService service;

    private final UserService userService;
    private final CartService cartService;

    public MenuPageController(MenuItemService s, UserService u, CartService cartService){
        this.service = s;
        this.userService = u;
        this.cartService = cartService;
    }

    @GetMapping("")
    public ModelAndView getMenu(ModelMap model){
        Collection<MenuItem> items  = service.getItems(new Sort(Sort.Direction.DESC, "id"));
        model.addAttribute("items", items);
        return new ModelAndView("menu", model);
    }

    @PostMapping("/addtocart")
    public ModelAndView addToCart(ModelMap model, @RequestParam("item_id") long itemId, @RequestParam("item_qty") int qty,
                            @RequestParam("item_notes") String itemNotes){

        // get user cart
        Cart cart = userService.getActiveCart(userService.getCurrentUser());

        service.find(itemId);

        CartItem cartItem = new CartItem(0, service.find(itemId), qty,itemNotes, cart);
        cartService.addToCart(cart, cartItem);

        return new ModelAndView("redirect:/menu", model);
    }

}
