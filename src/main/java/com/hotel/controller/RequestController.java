package com.hotel.controller;

import com.hotel.database.user.RolesDatabase;
import com.hotel.model.request.Request;
import com.hotel.model.user.ApplicationUser;
import com.hotel.model.user.Roles;
import com.hotel.service.request.RequestService;
import com.hotel.service.user.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Collection;
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
    public RedirectView makeGeneralRequestPost(ModelMap m, @RequestParam("category") String category, @RequestParam("roomno") int roomno,
                                               @RequestParam("request") String request) {
        boolean valid = false;
        Set<Roles> roles = userService.getCurrentUser().getUser_roles();
        if(roles.contains(rolesDatabase.find("ROLE_USER")) && userService.getCurrentUser().getRoom().getRoom() == roomno){
            valid = true;
        }
        else if(roles.contains(rolesDatabase.find("ROLE_STAFF"))){
            valid = true;
        }

        if(valid){
            long id = 0;
            Date d = new Date();
            d.setTime(System.currentTimeMillis());
            Request gr = Request.createGeneralRequest(id, roomno, d, request, category);
            requestService.add(gr);
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
        if(roles.contains(rolesDatabase.find("ROLE_USER")) && userService.getCurrentUser().getRoom().getRoom() == roomno){
            valid = true;
        }
        else if(roles.contains(rolesDatabase.find("ROLE_STAFF"))){
            valid = true;
        }

        if(valid){
            long id = 0;
            Date d = new Date();
            d.setTime(System.currentTimeMillis());
            Request mr = Request.createMaintenanceRequest(id, roomno, d, request, category);
            requestService.add(mr);
        }
        else{
            // idk some error maybe
        }
        return new RedirectView("/request");
    }

    @GetMapping("/wakeuprequests")
    public ModelAndView makeWakeUpRequest(ModelMap m) {
        return new ModelAndView("wakeuprequests", m);
    }

    @PostMapping("/wakeuprequests")
    public RedirectView makeWakeUpRequestPost(@RequestParam("roomno") int roomno, @RequestParam("hour") int hour, @RequestParam("minute") int minute){

        long id = 69;
        Date time = new Date();

        time.setTime(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, 1);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Request wr = Request.createWakeUpRequest(id, roomno, time, calendar.getTime());

        requestService.add(wr);

        return new RedirectView("/request");
    }

    @GetMapping("/requestsactive")
    public ModelAndView viewRequestsActive(ModelMap m, @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction, @RequestParam(value = "sort",
            required = false, defaultValue = "time") String sort) {

        /*
         * Get a list of all active requests
         */
        Collection<Request> requests = requestService.findByCompletion(new Sort(direction.equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC,
                sort), false);
        m.addAttribute("requests", requests);

        return new ModelAndView("requests", m);
    }

    @PostMapping("/completed")
    public RedirectView markCompleted (@RequestParam(value = "rid") long rid){

        Request requests = requestService.find(rid);

        requests.setCompleted(true);

        requestService.remove(rid);
        requestService.add(requests);

        return new RedirectView("/request/requestsactive");
    }



    @GetMapping("/log")
    public ModelAndView viewLog(ModelMap m, @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction, @RequestParam(value = "sort",
            required = false, defaultValue = "time") String sort) {

        /*
         * Get a list of all inactive requests
         */
        Collection<Request> requests = requestService.findByCompletion(new Sort(direction.equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC,
                sort), true);
        m.addAttribute("requests", requests);

        return new ModelAndView("log", m);
    }
}
