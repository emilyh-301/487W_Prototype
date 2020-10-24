package com.hotel.controller;

import com.hotel.model.GroupMember;
import com.hotel.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/database") //Start at database
public class DatabaseController {

    private final MemberService service;

    public DatabaseController(MemberService service) {
        this.service = service;
    }

    /*
     * URL-to-page mapping
     */

    /**
     * Open up the form to add a member
     * @param model The MVC Model
     * @return The HTML document name for the add form
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        return "add";
    }

    /**
     * Add the member added in the add form to the database and go back to the main page on submitting the add form
     * @return The URL of the home page
     */
    @PostMapping("/add")
    public String addSubmit(Model model, @RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                            @RequestParam("avatar") String avatar, @RequestParam("language") String language) {

        try {
            service.add(email, firstName, lastName, avatar, language);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "add";
        }

        ArrayList<GroupMember> members = service.getMembers();
        model.addAttribute("members", members);
        return "redirect:/";

    }

    /**
     * Open up the form to edit a member
     * @param model The MVC Model
     * @param email The email of the member to edit
     * @return The HTML document name for the add form
     */
    @GetMapping("/edit/{email}")
    public String editForm(Model model, @PathVariable String email) {
        model.addAttribute("member", service.findByEmail(email));

        return "edit";
    }

    /**
     * Add the member edited in the edit form to the database and go back to the main page on submitting the edit form
     * @return The the URL of the home page
     */
    @PostMapping("/edit")
    public String editSubmit(Model model, @RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                            @RequestParam("avatar") String avatar, @RequestParam("language") String language) {

        service.edit(email, firstName, lastName, avatar, language);
        model.addAttribute("members", service.getMembers());
        return "redirect:/";
    }

    /**
     * Remove the indicated member from the database
     * @return The the URL of the home page
     */
    @GetMapping("/remove/{email}")
    public String remove(Model model, @PathVariable String email) {
        service.remove(email);
        model.addAttribute("members", service.getMembers());
        return "redirect:/";
    }
}
