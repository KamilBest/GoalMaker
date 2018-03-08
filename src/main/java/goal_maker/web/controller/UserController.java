package goal_maker.web.controller;

import goal_maker.database.tables.Goal;
import goal_maker.database.tables.UserFinances;
import goal_maker.web.services.goal_service.GoalService;
import goal_maker.web.services.user_finances_service.UserFinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import goal_maker.database.tables.GmUser;
import goal_maker.web.services.user_service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserFinancesService userFinancesService;

    @Autowired
    GoalService goalService;

    @RequestMapping(value = "/getUserByLogin", method = RequestMethod.GET)
    public void getUserByLogin(Model model, @RequestParam String login) {
        GmUser gmUser = userService.getUserByLogin(login);
        model.addAttribute("location",  "userByLogin");
        model.addAttribute("User", gmUser);
    }

    @RequestMapping(value = "/getUsersList", method = RequestMethod.GET)
    public String getUsersList(Model model) {
        model.addAttribute("location", "usersList");
        model.addAttribute("usersList", userService.getUsersList());
        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
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
        UserFinances userFinances = new UserFinances(userFinancesService.nextId(), 0L,0L);
        user.setUserFinances(userFinances);
        userService.addUser(user);
        userFinancesService.addUserFinance(userFinances);

        return "redirect:/login";
    }

}

