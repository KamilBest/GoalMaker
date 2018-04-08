package goal_maker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SettingsController {


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showUserSettings(Model model) {
        model.addAttribute("location", "userSettings");

        return "index";
    }
}
