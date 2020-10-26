package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.service.item.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService service;

    public CheckoutController(CartService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String viewCheckout(Model model) {
        Cart cart = service.find(0);
        model.addAttribute("cart", cart);
        return "checkout";
    }
}
