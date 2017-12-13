package goal_maker.web.controller;

import goal_maker.database.dao.goal_dao.GoalDao;
import goal_maker.database.dao.goal_dao.GoalDao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class GoalController {
    @Autowired
    GoalDao goalDao;

}
