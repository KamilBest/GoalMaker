package goal_maker.web.controller;


import goal_maker.database.tables.Expenses;
import goal_maker.database.tables.GmUser;
import goal_maker.web.services.expenses_service.ExpensesService;
import goal_maker.web.services.user_finances_service.UserFinancesService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class ExpensesController {

    @Autowired
   private UserService userService;

    @Autowired
    private  ExpensesService expensesService;

    @Autowired
    private UserFinancesService userFinancesService;

    @RequestMapping(value ="/allExpenses", method = RequestMethod.GET)
    public String allExpenses(Model model)
    {
        model.addAttribute("location","expensesList");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);
        model.addAttribute("allExpenses", expensesService.findAllUserExpenses(gmUser.getUserFinances().getId_user_finances()));
        return "index";
    }

    @RequestMapping(value = "/addExpenses", method = RequestMethod.GET)
    public String addExpenses(Model model) {
        model.addAttribute("location", "addExpenses");
        model.addAttribute("expenses", new Expenses());
        return "index";
    }

    @RequestMapping(value = "/addExpenses", method = RequestMethod.POST)
    public String addExpenses(@RequestParam(value = "type")String type, @RequestParam(value = "value")long value, @RequestParam(value = "name")String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);
        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        Expenses expenses=new Expenses(type, value,name,gmUser.getUserFinances(),currentTime);

        expensesService.addExpenses(expenses);
        userFinancesService.updateRealAccountBalance(gmUser.getUserFinances(), expenses.getValue(), false);

        return "redirect:/index";
    }


    @RequestMapping(value = "/editExpense", method = RequestMethod.POST)
    public String editExpense(@RequestParam(value = "expenseId") long expenseId, @RequestParam(value = "name") String name, @RequestParam(value = "type") String type, @RequestParam(value = "value") long value) {
       Expenses expense=new Expenses(expenseId, name, type,value);
        expensesService.modifyExpense(expense);
        return "redirect:/allExpenses?expenseId=0";
    }

    @RequestMapping(value = "/deleteExpense", method = RequestMethod.GET)
    public String deleteIncome(@RequestParam(value = "expenseId") long expenseId) {
        expensesService.deleteExpense(expenseId);
        return "redirect:/allExpenses?expenseId=0";
    }
}
