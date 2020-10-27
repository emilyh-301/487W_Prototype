package com.hotel.controller;

import com.hotel.model.item.MenuItem;
import com.hotel.service.item.CartService;
import com.hotel.service.item.MenuItemService;
import com.hotel.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class SiteController {

    private final UserService userService;

    private final CartService cartService;

    private final MenuItemService service;

    public SiteController(UserService userService, CartService cartService, MenuItemService service) {
        this.userService = userService;
        this.cartService = cartService;
        this.service = service;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";

    }

    @GetMapping("/login_success")
    public String loginSuccess(Model model) {
        Collection<MenuItem> items  = service.getItems(new Sort(Sort.Direction.DESC, "id"));
        model.addAttribute("items", items);
        model.addAttribute("success", "You have been successfully logged in.");
        return "menu";
    }

    @GetMapping("/logout_success")
    public String logoutSuccess(Model model) {
        Collection<MenuItem> items  = service.getItems(new Sort(Sort.Direction.DESC, "id"));
        model.addAttribute("items", items);
        model.addAttribute("success", "You have been successfully logged out.");
        return "menu";
    }

    @GetMapping("/login_failure")
    public String loginFail(Model model) {

        model.addAttribute("failure", "Incorrect credentials.");
        return "login";

    }

    /**
     *
     * @param model The model for the MVC design pattern.
     * @return The name of the html document to display
     */
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }


}
