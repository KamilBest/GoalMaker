package goal_maker.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import goal_maker.database.tables.user.GmUser;
import goal_maker.web.services.user_service.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUserByLogin", method = RequestMethod.GET)
    public void getUserByLogin(Model model, @RequestParam String login) {
        GmUser gmUser = userService.getUserByLogin(login);
        model.addAttribute("location", "userByLogin");
        model.addAttribute("User", gmUser);
    }

    @RequestMapping(value = "/getUsersList", method = RequestMethod.GET)
    public void getUsersList(Model model) {
        model.addAttribute("location", "userByLogin");
        model.addAttribute("UsersList", userService.getUsersList());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUserForm(Model model) {
        model.addAttribute("user", new GmUser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") GmUser user) {
        userService.addUser(user);
        return "redirect:/login";
    }

}

