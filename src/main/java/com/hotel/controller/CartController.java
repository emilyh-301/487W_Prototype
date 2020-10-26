package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.service.item.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cart_service;

    public CartController(CartService cart_service) {
        this.cart_service = cart_service;
    }

    @GetMapping("")
    public String viewCart(Model model) {
        Cart cart = cart_service.find(0);
        if(cart != null)
            model.addAttribute("Cart", cart.getItems());
        else
            model.addAttribute("Cart", new HashSet<Integer>().add(301));
        return "cart";
    }
}
