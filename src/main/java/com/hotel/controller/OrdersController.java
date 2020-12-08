package com.hotel.controller;

import com.hotel.model.item.Cart;
import com.hotel.model.item.Order;
import com.hotel.model.request.Request;
import com.hotel.service.item.CartItemService;
import com.hotel.service.item.OrderService;
import com.hotel.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Collection;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;

    private final UserService userService;
    private final CartItemService cartService;


    public OrdersController(OrderService orderService, UserService userService, CartItemService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public ModelAndView viewOrders(ModelMap model){


            /*
             * Get a list of all active orders
             */
            Collection<Order> orders = orderService.getOrders(new Sort(Sort.Direction.ASC,"time"));

        model.addAttribute("orders",orders);

        return new ModelAndView("orders", model);
    }

    @GetMapping("/complete/{id}")
    public RedirectView removeItem(RedirectAttributes attributes, @PathVariable("id") Long id) {
        orderService.remove(id);
        attributes.addFlashAttribute("success", "You have successfully completed your order.");
        return new RedirectView("/orders");
    }
}
