package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.Goal;

public interface GoalDao {

    public Goal getGoalById(long id);

    public void addGoal(Goal goal);

    public void modifyGoal(Goal goal);

    public Goal getCurrentGoal(long userId);

    public void deleteGoal(long goalId);

}
