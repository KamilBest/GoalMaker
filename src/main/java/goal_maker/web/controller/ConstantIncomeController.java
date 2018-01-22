//package goal_maker.web.controller;
//
//import goal_maker.database.tables.ConstantIncome;
//import goal_maker.web.services.constant_income_service.ConstantIncomeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class ConstantIncomeController {
//    @Autowired
//    ConstantIncomeService constantIncomeService;
//
//
//    @RequestMapping(value = "/addConstantIncome", method = RequestMethod.GET)
//    public String addConstantIncome(Model model) {
//        model.addAttribute("location", "createConstantIncomeForm");
//        model.addAttribute("changeableIncome", new ConstantIncome());
//        return "index";
//    }
//
//    @RequestMapping(value = "/addConstantIncome", method = RequestMethod.POST)
//    public String addConstantIncome(@RequestParam(value = "name")String name, @RequestParam(value = "value")long value, @RequestParam(value = "days")long howManyDays) {
//        ConstantIncome changeableIncome=new ConstantIncome(name,value,howManyDays);
//        constantIncomeService.addConstantIncome(changeableIncome);
//        return "redirect:/index";
//    }
//}
