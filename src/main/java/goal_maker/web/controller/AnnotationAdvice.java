package goal_maker.web.controller;

import goal_maker.database.dao.goal_dao.GoalDao;
import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Goal;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@ControllerAdvice(annotations = Controller.class)
public class AnnotationAdvice {

    @Autowired
    UserService userService;

    @Autowired
    GoalDao goalDao;

    @ModelAttribute("realAccountBalance")
    public Long userFinances(){
        //get current logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long realAccountBalance;
        String login = auth.getName(); //get logged in login
        if(login == "anonymousUser"){
            realAccountBalance = null;
        }else{
            GmUser gmUser = userService.getUserByLogin(login);
            if(gmUser.getUserFinances().getReal_account_balance() == null){
                realAccountBalance = 0L;
            }else{
                realAccountBalance = gmUser.getUserFinances().getReal_account_balance();
            }
        }
        return realAccountBalance;
    }

    @ModelAttribute("currentUserGoalBalance")
    public Long currentUserGoalBalance(){
        //get current logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userGoalBalance;
        String login = auth.getName(); //get logged in login
        if(login == "anonymousUser"){
            userGoalBalance = null;
        }else{
            GmUser gmUser = userService.getUserByLogin(login);
            if(gmUser.getUserFinances().getGoal_balance() == null){
                userGoalBalance = 0L;
            }else{
                userGoalBalance = gmUser.getUserFinances().getGoal_balance();
            }
        }
        return userGoalBalance;
    }

    @ModelAttribute("currentUserGoalValue")
    public Long currentUserGoalValue(){
        //get current logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userGoalValue;
        String login = auth.getName(); //get logged in login
        if(login == "anonymousUser"){
            userGoalValue = 0L;
        }else{
            GmUser gmUser = userService.getUserByLogin(login);
            Goal currentGoal = goalDao.getCurrentGoal(gmUser.getId());
            if(currentGoal == null){
                userGoalValue = 0L;
            }
            else{
                userGoalValue = currentGoal.getValue();
            }
        }
        return userGoalValue;
    }
}
