package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.model.item.CartItem;
import com.hotel.model.item.Order;
import com.hotel.service.item.CartService;
import com.hotel.service.item.OrderService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService service;
    private final UserService userService;
    private final OrderService orderService;

    public CheckoutController(CartService service, UserService userService, OrderService orderService) {
        this.service = service;
        this.userService = userService;
        this.orderService = orderService;
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

    @PostMapping("/")
    public RedirectView Checkout(RedirectAttributes attributes) {
        try {
            Cart cart = userService.getActiveCart(userService.getCurrentUser());
            if(cart != null) {
                for (CartItem ci : cart.getItems()) {
                    if(ci.getQuantity() <= 0) {
                        attributes.addFlashAttribute("failure", "Item cannot have quantity of 0 or less!");
                        return new RedirectView("/cart");
                    }
                    if(ci.getItem().getPrice().doubleValue() < 0) {
                        attributes.addFlashAttribute("failure", "Item cannot have negative price!");
                        return new RedirectView("/cart");
                    }
                }
            }
            else {
                attributes.addFlashAttribute("failure", "Not a valid cart!");
                return new RedirectView("/cart");
            }

            Order order = new Order();
            order.setId(1);
            order.setCart(cart);
            order.setStatus(Order.Status.RECEIVED);
            order.setTime(System.currentTimeMillis());
            orderService.add(order);

            service.setCompleted(cart.getId(), true);

            attributes.addFlashAttribute("success", "You have successfully submitted your order.");
            return new RedirectView("/menu");
        } catch (Exception e) {
            attributes.addFlashAttribute("false", "Oops! Something went wrong submitting your order.");
            return new RedirectView("/menu");
        }



    }

    @GetMapping("/clear")
    public RedirectView checkout(RedirectAttributes attributes) {
        Cart cart = userService.getActiveCart(userService.getCurrentUser());
        attributes.addFlashAttribute("success", "You have successfully submitted your order.");
        service.clearCart(cart);

        return new RedirectView("/menu");
    }
}
