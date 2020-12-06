package com.hotel.controller;

import com.hotel.database.user.RolesDatabase;
import com.hotel.model.request.Request;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Roles;
import com.hotel.service.request.RequestService;
import com.hotel.service.user.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/request")
public class RequestController {

    private UserService userService;
    private RequestService requestService;
    private RolesDatabase rolesDatabase;

    public RequestController(UserService u, RequestService r, RolesDatabase rolesDatabase) {
        userService = u;
        requestService = r;
        this.rolesDatabase = rolesDatabase;
    }

    @GetMapping("")
    public ModelAndView requestMenu(ModelMap m) {
        return new ModelAndView("requestMenu", m);
    }

    @GetMapping("/general")
    public ModelAndView makeGeneralRequest(ModelMap m) {
        return new ModelAndView("generalrequests", m);
    }

    @PostMapping("/general")
    public RedirectView makeGeneralRequestPost(ModelMap m, @RequestParam("category") String category, @RequestParam("roomnumber") int roomnumber,
                                               @RequestParam("request") String request) {
        boolean valid = false;
        Set<Roles> roles = userService.getCurrentUser().getUser_roles();
        if(roles.contains(rolesDatabase.find("ROLE_USER")) && userService.getCurrentUser().getRoom().getRoom() == roomnumber){
            valid = true;
        }
        else if(roles.contains(rolesDatabase.find("ROLE_STAFF"))){
            valid = true;
        }

        if(valid){
            long id = 0; // need a way to increment this
            Date d = new Date();
            d.setTime(System.currentTimeMillis());
            Request gr = Request.createGeneralRequest(id, roomnumber, d, request, category);
            requestService.add(gr);
            /*
            GeneralRequest gr = new GeneralRequest(id, roomno, d, request, category);
            generalRequestService.add(gr);

             */
        }
        else{
            // idk some error maybe
        }
        return new RedirectView("/request");
    }

    @GetMapping("/maintenance")
    public ModelAndView makeMaintenanceRequest(ModelMap m) {
        return new ModelAndView("maintenancerequests", m);
    }

    @PostMapping("/maintenance")
    public RedirectView makeMaintenanceRequestPost(ModelMap m, @RequestParam("category") String category, @RequestParam("roomno") int roomno,
                                                   @RequestParam("request") String request) {
        boolean valid = false;
        Set<Roles> roles = userService.getCurrentUser().getUser_roles();
        if(roles.contains("ROLE_USER") && userService.getCurrentUser().getRoom().getRoom() == roomno){
            valid = true;
        }
        else if(roles.contains("ROLE_STAFF")){
            valid = true;
        }

        if(valid){
            long id = 0; // need a way to increment this
            Date d = new Date();
            d.setTime(System.currentTimeMillis());

            Request mr = Request.createMaintenanceRequest(id, roomno, d, request, category);
            requestService.add(mr);

            /*
            MaintenanceRequest mr = new MaintenanceRequest(id, roomno, d, request, category);
            maintenanceRequestService.add(mr);

             */
        }
        else{
            // idk some error maybe
        }
        return new RedirectView("/requests");
    }



}
