package goal_maker.web.controller;

import goal_maker.database.tables.GmUser;
import goal_maker.web.services.user_finances_service.UserFinancesService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import goal_maker.web.services.user_finances_service.UserFinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserFinancesController {

    @Autowired
    UserService userService;
    @Autowired
    UserFinancesService userFinancesService;

    @RequestMapping(value = "/updateGoalBalance", method = RequestMethod.GET)
    public String updateGoalBalance(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);

        model.addAttribute("location", "accountMoneyTransfer");
        model.addAttribute("realAccountBalance", gmUser.getUserFinances().getReal_account_balance());
        return "index";    }

    @RequestMapping(value = "/updateGoalBalance", method = RequestMethod.POST)
    public String updateGoalBalance(@RequestParam(value = "value") long value) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);

        //update goal balance
        userFinancesService.updateGoalBalance(value, gmUser.getUserFinances());

        //update real account balance
        boolean isIncome = false;
        userFinancesService.updateRealAccountBalance(gmUser.getUserFinances(), value, isIncome);
        return "redirect:/index";
    }
}
