package com.hotel.controller;

import com.hotel.model.item.Allergen;
import com.hotel.model.item.MenuItem;
import com.hotel.service.item.CartService;
import com.hotel.service.item.MenuItemService;
import com.hotel.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

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

    private boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) return false;

        return auth.isAuthenticated();
    }

    @GetMapping("/login")
    public ModelAndView login(ModelMap model) {
        return new ModelAndView(isAuthenticated()? "redirect:/" : "login", model);
    }

    @GetMapping("/add")
    public ModelAndView addItem(ModelMap model) {
        return new ModelAndView("add", model);
    }

    @PostMapping("/add")
    public ModelAndView addItemPost(ModelMap model, @RequestParam("item_name") String item_name, @RequestParam("description") String description,
                                    @RequestParam("price") String price, @RequestParam("allergens") String allergens, @RequestParam("image") String image) {
        HashSet<Allergen> allergenSet = new HashSet<>();
        String[] allergen_array = allergens.split(",");
        for(String s : allergen_array) allergenSet.add(new Allergen(s));

        service.add(new MenuItem(0, item_name, allergenSet, Double.parseDouble(price), description, image));
        return new ModelAndView("add", model);
    }

    @GetMapping("/login_success")
    public RedirectView loginSuccess(RedirectAttributes attributes) {
        return new RedirectView("");
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
    @GetMapping("")
    public String home(Model model) {
        return "home";
    }


}
