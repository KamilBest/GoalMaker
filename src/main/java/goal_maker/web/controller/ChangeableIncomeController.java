//package goal_maker.web.controller;
//
//import goal_maker.database.tables.ChangeableIncome;
//import goal_maker.web.services.changeable_income_service.ChangeableIncomeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class ChangeableIncomeController {
//    @Autowired
//    ChangeableIncomeService changeableIncomeService;
//
//    @RequestMapping(value = "/addChangeableIncome", method = RequestMethod.GET)
//    public String addGoal(Model model) {
//        model.addAttribute("location", "createChangeableIncomeForm");
//        model.addAttribute("changeableIncome", new ChangeableIncome());
//        return "index";
//    }
//
//    @RequestMapping(value = "/addChangeableIncome", method = RequestMethod.POST)
//    public String addGoal(@RequestParam(value = "name")String name, @RequestParam(value = "value")long value) {
//        ChangeableIncome changeableIncome=new ChangeableIncome(name,value);
//        changeableIncomeService.addChangeableIncome(changeableIncome);
//        return "redirect:/index";
//    }
//}
