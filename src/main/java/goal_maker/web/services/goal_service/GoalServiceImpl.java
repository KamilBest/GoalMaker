package goal_maker.web.services.goal_service;

import goal_maker.database.dao.goal_dao.GoalDao;
import goal_maker.database.tables.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GoalService")
public class GoalServiceImpl implements GoalService {
    @Autowired
    GoalDao goalDao;

    @Override
    public Goal getGoalById(long id) {
        return goalDao.getGoalById(id);
    }

}
