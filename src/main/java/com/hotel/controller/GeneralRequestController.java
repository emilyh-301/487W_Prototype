package com.hotel.controller;

import com.hotel.model.request.GeneralRequest;
import com.hotel.service.request.GeneralRequestService;
import com.hotel.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/generalrequest")
public class GeneralRequestController {

    private UserService userService;
    private GeneralRequestService generalRequestService;

    public GeneralRequestController(UserService u, GeneralRequestService g) {
        userService = u;
        generalRequestService = g;
    }


}
