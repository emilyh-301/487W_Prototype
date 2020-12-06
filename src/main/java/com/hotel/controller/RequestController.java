package com.hotel.controller;

import com.hotel.model.request.GeneralRequest;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Roles;
import com.hotel.service.request.GeneralRequestService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("/request")
public class RequestController {

    private UserService userService;
    private GeneralRequestService generalRequestService;

    public RequestController(UserService u, GeneralRequestService g) {
        userService = u;
        generalRequestService = g;
    }

    @GetMapping("")
    public ModelAndView requestMenu(ModelMap m) {
        return new ModelAndView("requestMenu", m);
    }

    @GetMapping("/general")
    public ModelAndView makeGeneralRequest(ModelMap m) {
        return new ModelAndView("generalrequests", m);
    }

/*    @PostMapping("/general")
    public ModelAndView makeGeneralRequestPost(ModelMap m, @RequestParam("category") String category, @RequestParam("roomno") int roomno,
                                               @RequestParam("request") String request) {


    }*/


}
