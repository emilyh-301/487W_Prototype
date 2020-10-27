package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.service.item.CartService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cart_service;

    private final UserService userService;

    public CartController(CartService cart_service, UserService userService) {
        this.cart_service = cart_service;
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView viewCart(ModelMap model) {
        Cart cart = userService.getActiveCart(userService.getCurrentUser());

        if(cart != null)
            model.addAttribute("Cart", cart.getItems());
        else
            model.addAttribute("Cart", new HashSet<Integer>());
        return new ModelAndView("cart", model);
    }
}
