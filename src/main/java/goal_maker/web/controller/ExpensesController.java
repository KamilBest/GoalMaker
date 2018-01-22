package goal_maker.web.controller;


import goal_maker.database.tables.Expenses;
import goal_maker.database.tables.GmUser;
import goal_maker.web.services.expenses_service.ExpensesService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ExpensesController {

    @Autowired
   private UserService userService;

    @Autowired
    private  ExpensesService expensesService;

    @RequestMapping(value = "/addExpenses", method = RequestMethod.GET)
    public String addExpenses(Model model) {
        model.addAttribute("location", "addExpenses");
        model.addAttribute("expenses", new Expenses());
        return "index";
    }

    @RequestMapping(value = "/addExpenses", method = RequestMethod.POST)
    public String addExpenses(@RequestParam(value = "type")String type, @RequestParam(value = "value")long value, @RequestParam(value = "date")Date date, @RequestParam(value = "name")String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);
        Expenses expenses=new Expenses(type, value,name,gmUser.getUserFinances(),date);

        expensesService.addExpenses(expenses);
        return "redirect:/index";
    }
}
