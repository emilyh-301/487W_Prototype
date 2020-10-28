package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.service.item.CartService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

        model.addAttribute("Cart", cart == null? new HashSet<>() : cart.getItems());
        return new ModelAndView("cart", model);
    }

    @GetMapping("/remove/{id}")
    public RedirectView removeItem(RedirectAttributes attributes, @PathVariable("id") Long id) {
        Cart cart = userService.getActiveCart(userService.getCurrentUser());
        cart_service.removeFromCart(cart, id);

        return new RedirectView("/cart");
    }

    @GetMapping("/clear")
    public RedirectView clearCart(RedirectAttributes attributes) {
        Cart cart = userService.getActiveCart(userService.getCurrentUser());
        attributes.addFlashAttribute("success", "You have successfully emptied the cart.");
        cart_service.clearCart(cart);

        return new RedirectView("/menu");
    }

}
