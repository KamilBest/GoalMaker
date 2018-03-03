package goal_maker.web.controller;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Income;
import goal_maker.web.services.income_service.IncomeService;
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
public class IncomeController {

    @Autowired
    UserService userService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    UserFinancesService userFinancesService;

    @RequestMapping(value ="/allIncomes", method = RequestMethod.GET)
    public String allIncomes(Model model, @RequestParam(value = "incomeId")long incomeId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);

        if(incomeId>0) {
            Income chosenIncome = incomeService.getIncomeById(incomeId);
            model.addAttribute("chosenIncome", chosenIncome);
        }
        else
            model.addAttribute("chosenIncome", null);
        model.addAttribute("location","incomesList");
        model.addAttribute("allIncomes", incomeService.findAllUserIncomes(gmUser.getUserFinances().getId_user_finances()));
        return "index";
    }
    @RequestMapping(value = "/addIncome", method = RequestMethod.GET)
    public String addIncome(Model model) {
        model.addAttribute("location", "addIncome");
        model.addAttribute("income", new Income());
        return "index";
    }

    @RequestMapping(value = "/addIncome", method = RequestMethod.POST)
    public String addIncome(@RequestParam(value = "type")String type,@RequestParam(value = "name")String name, @RequestParam(value = "value")long value) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);


        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        Income income=new Income(type,value,gmUser.getUserFinances(),currentTime,name);

        incomeService.addIncome(income);

        //update user current state to goal
        userFinancesService.updateCurrentStateToGoal(income);
        userFinancesService.updateAccountBalance(gmUser.getUserFinances(), income.getValue(), true);
        return "redirect:/index";
    }

/*
    @RequestMapping(value = "/editIncome", method = RequestMethod.GET)
    public String editIncome(Model model, @RequestParam(value = "incomeId") long incomeId) {
        Income income=incomeService.getIncomeById(incomeId);
        model.addAttribute( "chosenIncome", income);
        return "redirect:/allIncomes";
    }*/
    /**
     * Income edit
     * @param model
     * @param id given income id
     * @return
     */

    @RequestMapping(value = "/editIncome", method = RequestMethod.POST)
    public String editIncome(@RequestParam(value = "incomeId") long incomeId, @RequestParam(value = "name") String name, @RequestParam(value = "type") String type, @RequestParam(value = "value") long value) {
        Income income=new Income(incomeId,name, type, value);
        incomeService.modifyIncome(income);
        return "redirect:/allIncomes?incomeId=0";
    }

    @RequestMapping(value = "/deleteIncome", method = RequestMethod.GET)
    public String deleteIncome(@RequestParam(value = "incomeId") long incomeId) {
        incomeService.deleteIncome(incomeId);
        return "redirect:/allIncomes?incomeId=0";
    }
}
