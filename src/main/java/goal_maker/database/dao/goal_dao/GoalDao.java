package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.Goal;

import java.util.List;

public interface GoalDao {

    public Goal getGoalById(long id);

    public List<Goal> getGoalsByState(long idGoalState);
    public void addGoal(Goal goal);

    public void modifyGoal(Goal goal);

    public Goal getCurrentGoal(long userId);

    public void deleteGoal(long goalId);

    public void changeGoalState(long goalId, long idGoalState);

    Goal getLastRealisedGoal(long idGoalState);

    List<Goal> getGoalsByStateAndUserId(long idGoalState, long userId);
}
