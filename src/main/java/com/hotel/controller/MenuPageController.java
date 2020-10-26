package com.hotel.controller;

import com.hotel.model.item.MenuItem;
import com.hotel.service.item.MenuItemService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;

@Controller
@RequestMapping("/menu")
public class MenuPageController {
    private final MenuItemService service;

    public MenuPageController(MenuItemService s){
        this.service = s;
    }

    @GetMapping("")
    public String getMenu(Model model){
        Collection<MenuItem> items  = service.getItems(new Sort(Sort.Direction.DESC, "id"));
        model.addAttribute("items", items);
        return "menu";
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
}
