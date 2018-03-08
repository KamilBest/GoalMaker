package goal_maker.web.controller;

import goal_maker.database.tables.Category;
import goal_maker.web.services.category_service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;



    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addCategory(Model model) {
      //  model.addAttribute("location","addCategory");
        model.addAttribute("category",new Category());
        return "index";
    }
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@RequestParam(value = "categoryName")String categoryName ){
        Category category = new Category(categoryName);
        categoryService.addCategory(category);

        return "redirect:/index";
    }


    @RequestMapping(value = "/editCategory", method = RequestMethod.GET)
    public String editCategory(Model model, @RequestParam("categoryId") long categoryId) {
        model.addAttribute("currentCategory", categoryService.getCategoryById(categoryId));
        return "index";
    }

    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public String editCategory(@RequestParam(value = "categoryId") long categoryId, @RequestParam(value = "newCategoryName") String newCategoryName) {
        categoryService.editCategory(categoryId,newCategoryName);
        return "redirect:/index";
    }
}
