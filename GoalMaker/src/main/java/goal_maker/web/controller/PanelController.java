package goal_maker.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PanelController {
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String showFrontPage(Model model, final Principal principal) {
		
		return "index";
	}

}
