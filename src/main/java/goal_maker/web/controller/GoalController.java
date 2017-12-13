package goal_maker.web.controller;


import goal_maker.database.tables.Goal;
import goal_maker.web.services.goal_service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoalController {

    @Autowired
    GoalService goalService;

    @RequestMapping(value = "/addGoal", method = RequestMethod.GET)
    public String addGoal(Model model) {
        model.addAttribute("location", "CreateUpdateGoal");
        model.addAttribute("goal", new Goal());
        return "index";
    }

    @RequestMapping(value = "/addGoal", method = RequestMethod.POST)
    public String addGoal(@ModelAttribute("goal") Goal goal) {
        goalService.addGoal(goal);
        return "redirect:/index";
    }
}
