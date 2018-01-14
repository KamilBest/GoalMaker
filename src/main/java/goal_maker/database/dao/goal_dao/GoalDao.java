package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.Goal;

public interface GoalDao {

    public Goal getGoalById(long id);
    public void addGoal(Goal goal);


}
