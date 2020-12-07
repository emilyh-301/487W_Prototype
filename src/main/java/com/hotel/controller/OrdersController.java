package com.hotel.controller;

import com.hotel.service.item.CartItemService;
import com.hotel.service.item.OrderService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService service;

    private final UserService userService;
    private final CartItemService cartService;


    public OrdersController(OrderService service, UserService userService, CartItemService cartService) {
        this.service = service;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public ModelAndView viewOrders(ModelMap model){

        return new ModelAndView("orders", model);
    }
}
