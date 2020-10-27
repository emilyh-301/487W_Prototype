package com.hotel.controller;

import com.hotel.model.item.MenuItem;
import com.hotel.service.item.CartService;
import com.hotel.service.item.MenuItemService;
import com.hotel.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public ModelAndView login(ModelMap model) {
        return new ModelAndView("login", model);

    }

    @GetMapping("/login_success")
    public RedirectView loginSuccess(RedirectAttributes attributes) {
        return new RedirectView("/menu");
    }

    @GetMapping("/logout_success")
    public RedirectView logoutSuccess(RedirectAttributes attributes) {
        attributes.addFlashAttribute("success", "You have been successfully logged out.");
        return new RedirectView("login", true);
    }

    @GetMapping("/login_failure")
    public RedirectView loginFail(RedirectAttributes attributes) {
        attributes.addFlashAttribute("failure", "Incorrect credentials.");
        return new RedirectView("login", true);
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
