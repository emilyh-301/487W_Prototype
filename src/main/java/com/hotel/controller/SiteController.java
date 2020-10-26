package com.hotel.controller;

import com.hotel.model.GroupMember;
import com.hotel.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class SiteController {

    private final MemberService service;

    public SiteController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";

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
