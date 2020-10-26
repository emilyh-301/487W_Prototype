package com.hotel.controller;

import com.hotel.model.user.ApplicationUser;
import com.hotel.service.item.CartService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    private final UserService userService;

    private final CartService cartService;

    public SiteController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";

    }

    @GetMapping("/login_success")
    public String loginSuccess(Model model) {
        ApplicationUser user = userService.getCurrentUser();
        if(!user.hasActiveCart()) {
            userService.createEmptyCart(user);
        }

        return "redirect:/menu";
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
