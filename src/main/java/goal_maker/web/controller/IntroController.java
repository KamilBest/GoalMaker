package goal_maker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntroController {


    @RequestMapping(value = {"/","/intro"}, method = RequestMethod.GET)
    public String showFrontPage(Model model) {
        return "introPage";
    }
}
