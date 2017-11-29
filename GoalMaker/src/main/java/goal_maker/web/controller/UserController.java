package goal_maker.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import goal_maker.database.tables.user.GmUser;
import goal_maker.web.services.user_service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/getUserByLogin", method=RequestMethod.GET)
	public void getUserByLogin(Model model, @RequestParam String login)
	{
		GmUser gmUser = userService.getUserByLogin(login);
		model.addAttribute("location", "userByLogin");
		model.addAttribute("User", gmUser);
	}

	@RequestMapping(value="/getUsersList", method=RequestMethod.GET)
	public void getUsersList(Model model)
	{
		model.addAttribute("location", "userByLogin");
		model.addAttribute("UsersList", userService.getUsersList());
	}


	
}

