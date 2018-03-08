package goal_maker.web.controller;

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
    UserFinancesService userFinancesService;

    @RequestMapping(value = "/editRealAccountBalance", method = RequestMethod.GET)
    public String editRealAccountBalance(Model model, @RequestParam("userFinancesId") long userFinancesId) {

        model.addAttribute("currentUserFinances", userFinancesService.getUserFinanceById(userFinancesId));
        return "index";
    }

    @RequestMapping(value = "/editRealAccountBalance", method = RequestMethod.POST)
    public String editRealAccountBalance(@RequestParam(value = "userFinancesId") long userFinancesId, @RequestParam(value = "newRealAccountBalance") Long newRealAccountBalance) {
        userFinancesService.updateRealAccountBalance(userFinancesId, newRealAccountBalance);
        return "redirect:/index";
    }


}
