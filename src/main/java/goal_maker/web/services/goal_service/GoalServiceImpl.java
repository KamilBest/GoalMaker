package goal_maker.web.services.goal_service;

import goal_maker.database.dao.goal_dao.GoalDao;
import goal_maker.database.tables.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GoalService")
public class GoalServiceImpl implements GoalService {
    @Autowired
    GoalDao goalDao;

    @Override
    public Goal getGoalById(long id) {
        return goalDao.getGoalById(id);
    }

    @Override
    public List<Goal> getGoalsByState(long idGoalState) {
        return goalDao.getGoalsByState(idGoalState);
    }

    @Override
    public void addGoal(Goal goal) {
        goalDao.addGoal(goal);
    }

    @Override
    public void modifyGoal(Goal goal) {
        goalDao.modifyGoal(goal);
    }

    @Override
    public Goal getCurrentGoal(long userId) {
        return goalDao.getCurrentGoal(userId);
    }

    @Override
    public void deleteGoal(long goalId) {
        goalDao.deleteGoal(goalId);
    }

    @Override
    public void changeGoalState(long goalId, long idGoalState) {
        goalDao.changeGoalState(goalId,idGoalState);
    }


}
