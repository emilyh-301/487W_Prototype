package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.model.item.CartItem;
import com.hotel.service.item.CartService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService service;
    private final UserService userService;

    public CheckoutController(CartService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView viewCheckout(ModelMap model) {
        Cart cart = userService.getActiveCart(userService.getCurrentUser());
        double sum = 0;
        if(cart != null) {
            for (CartItem ci : cart.getItems()) {
                sum += ci.getQuantity() * ci.getItem().getPrice().doubleValue();
            }
        }
        model.addAttribute("cart", cart);
        model.addAttribute("total", String.format("%.2f", sum));
        return new ModelAndView("checkout", model);
    }

    @GetMapping("/clear")
    public RedirectView checkout(RedirectAttributes attributes) {
        Cart cart = userService.getActiveCart(userService.getCurrentUser());
        attributes.addFlashAttribute("success", "You have successfully submitted your order.");
        service.clearCart(cart);

        return new RedirectView("/menu");
    }
}
