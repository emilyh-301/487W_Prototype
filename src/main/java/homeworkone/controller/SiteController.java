package homeworkone.controller;

import homeworkone.GroupMember;
import homeworkone.service.MemberService;
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

    /**
     *
     * @param model The model for the MVC design pattern.
     * @return The name of the html document to display
     */
    @GetMapping("/")
    public String home(Model model) {

        /*
         * Add the members from the db to the model so we can pass it along to the view.
         */
        ArrayList<GroupMember> members = service.getMembers();
        model.addAttribute("members", members);
        return "home";
    }
}
