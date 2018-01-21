package goal_maker.web.controller;

import goal_maker.database.dao.goal_dao.GoalDao;
import goal_maker.database.tables.GmUser;
import goal_maker.web.services.income_service.IncomeService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    @Autowired
    GoalDao goalDao;

    @Autowired
    UserService userService;

    @Autowired
    IncomeService incomeService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String showFrontPage(Model model) {
        model.addAttribute("location", "dashboard");


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);

        if (gmUser.getGoal() != null)
            model.addAttribute("currentUserGoal", goalDao.getGoalById(gmUser.getGoal().getId_goal()));
        else
            model.addAttribute("currentUserGoal", gmUser.getGoal());
        model.addAttribute("incomeList", incomeService.findAllUserIncomes(gmUser.getUserFinances().getId_user_finances()));
        return "index";
    }


}
