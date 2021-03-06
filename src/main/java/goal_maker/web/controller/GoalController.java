package goal_maker.web.controller;

import goal_maker.database.tables.Category;
import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Goal;
import goal_maker.web.services.category_service.CategoryService;
import goal_maker.web.services.goal_service.GoalService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GoalController {

    @Autowired
    GoalService goalService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addGoal", method = RequestMethod.GET)
    public String addGoal(Model model) {
        model.addAttribute("location", "createUpdateGoal");
        model.addAttribute("goal", new Goal());
        model.addAttribute("goalCategories", categoryService.findAll());
        return "index";
    }

    @RequestMapping(value = "/addGoal", method = RequestMethod.POST)
    public String addGoal(@RequestParam(value = "name") String name, @RequestParam(value = "categoryId") long categoryId, @RequestParam(value = "value") long value) {
        Category category = categoryService.getCategoryById(categoryId);
        Goal goal = new Goal(name, category, value);
        goalService.addGoal(goal);
        return "redirect:/index";
    }

    @RequestMapping(value = "/addOrEditGoal", method = RequestMethod.GET)
    public String editGoal(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("location", "addOrEditGoal");

        model.addAttribute("goalCategories", categoryService.findAll());

        if (id == null) {
            model.addAttribute("goal", new Goal());
            model.addAttribute("insertion", true);

        } else {
            model.addAttribute("goal", goalService.getGoalById(id));
            model.addAttribute("insertion", false);
        }
        return "index";
    }

    @RequestMapping(value = "/addOrEditGoal", method = RequestMethod.POST)
    public String editGoal(@RequestParam(value = "id") long id, @RequestParam(value = "insertion") boolean insertion, @RequestParam(value = "name") String name, @RequestParam(value = "categoryId") long categoryId, @RequestParam(value = "value") long value) {
        Category category = categoryService.getCategoryById(categoryId);
        Goal goal = new Goal(id, name, category, value);
        if (insertion) {
            goalService.addGoal(goal);

        } else
            goalService.modifyGoal(goal);
        return "redirect:/index";
    }

    @RequestMapping(value = "/deleteGoal", method = RequestMethod.GET)
    public String deleteGoal(@RequestParam(value = "id") long id) {
        goalService.deleteGoal(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/allRealisedGoals", method = RequestMethod.GET)
    public String allRealisedGoals(Model model){
        model.addAttribute("location", "realisedGoalList");
        //get current logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);
        final long REALISED = 1;
        model.addAttribute("realisedGoals", goalService.getGoalsByStateAndUserId(REALISED, gmUser.getId()));
        return "index";

    }

}
