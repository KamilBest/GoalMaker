package goal_maker.web.controller;
import goal_maker.database.tables.Category;
import goal_maker.database.tables.Goal;
import goal_maker.web.services.category_service.CategoryService;
import goal_maker.web.services.goal_service.GoalService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GoalController {

    @Autowired
    GoalService goalService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "/addGoal", method = RequestMethod.GET)
    public String addGoal(Model model) {
        model.addAttribute("location", "createUpdateGoal");
        model.addAttribute("goal", new Goal());
        model.addAttribute("goalCategories", categoryService.findAll());
        return "index";
    }

    @RequestMapping(value = "/addGoal", method = RequestMethod.POST)
    public String addGoal(@RequestParam(value = "name")String name, @RequestParam(value = "categoryId")long categoryId, @RequestParam(value = "value")long value) {
        Category category=categoryService.getCategoryById(categoryId);
        Goal goal=new Goal(name,category,value);
        goalService.addGoal(goal);
        return "redirect:/index";
    }
    @RequestMapping(value = "/editGoal", method = RequestMethod.GET)
    public String editGoal(Model model, @RequestParam("id") long id){
        model.addAttribute("location", "updateGoal");
        model.addAttribute("goal", goalService.getGoalById(id));
        model.addAttribute("goalCategories", categoryService.findAll());
        return "index";
    }
    @RequestMapping(value = "/editGoal", method = RequestMethod.POST)
    public String editGoal(@RequestParam(value = "id") long id, @RequestParam(value = "name") String name, @RequestParam(value = "categoryId") long categoryId, @RequestParam(value = "value")long value){
        Category category = categoryService.getCategoryById(categoryId);
        Goal goal = new Goal(id, name, category, value);
        goalService.modifyGoal(goal);
        return "redirect:/index";
    }

}
