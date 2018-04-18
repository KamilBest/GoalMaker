package goal_maker.web.controller;

import goal_maker.config.security.Encryption;
import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.UserFinances;
import goal_maker.web.services.user_finances_service.UserFinancesService;
import goal_maker.web.services.user_service.UserService;
import goal_maker.web.services.user_settings_service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    UserSettingsService userSettingsService;

    @Autowired
    UserFinancesService userFinancesService;

    @Autowired
    @Qualifier("encryption")
    Encryption encryption;


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showUserSettings(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);
        UserFinances userFinances = userFinancesService.getUserFinanceById(gmUser.getUserFinances().getId_user_finances());
        model.addAttribute("location", "userSettings");
        model.addAttribute("user", gmUser);
        model.addAttribute("userFinances", userFinances);

        return "index";
    }


    @RequestMapping(value = "/editUserPassword", method = RequestMethod.POST)
    public String editUserPasword(
            @RequestParam(value = "password") String password,
            @RequestParam(value = "newPassword") String newPassword,
            @RequestParam(value = "repeatNewPassword") String repeatNewPassword) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);
        Boolean compatible = false;

        //compare  newpassword with repeatNewPassword to avoid mistake
        if (newPassword.equals(repeatNewPassword)) {
            compatible = true;
        }
        //compare password with hash from DB
        if (encryption.comparePassword(password, gmUser.getPassword()) && compatible) {
            //set new password

            userSettingsService.editUserSettings(gmUser, "password", encryption.encryptPassword(newPassword));
        }


        return "redirect:/index";
    }


    @RequestMapping(value = "/editUserEmail", method = RequestMethod.POST)
    public String editUserEmail(@RequestParam(value = "newEmail") String newEmail) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);

        //edit field
        userSettingsService.editUserSettings(gmUser, "email", newEmail);

        return "redirect:/index";
    }

    @RequestMapping(value = "/editRealAccountBalance", method = RequestMethod.POST)
    public String editAccountBalance(@RequestParam(value = "newAccountBalance") long newAccountBalance) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);

        //find user finances
        UserFinances userFinances = userFinancesService.getUserFinanceById(gmUser.getUserFinances().getId_user_finances());


        //edit field
        userSettingsService.editUserFinances(userFinances, "real_account_balance", newAccountBalance);

        return "redirect:/index";
    }

    @RequestMapping(value = "/editGoalBalance", method = RequestMethod.POST)
    public String editGoalBalance(@RequestParam(value = "newGoalBalance") long newGoalBalance) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        GmUser gmUser = userService.getUserByLogin(login);

        //find user finances
        UserFinances userFinances = userFinancesService.getUserFinanceById(gmUser.getUserFinances().getId_user_finances());


        //edit field
        userSettingsService.editUserFinances(userFinances, "goal_balance", newGoalBalance);

        return "redirect:/index";
    }
}



