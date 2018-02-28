package goal_maker.web.services.goal_service;

import goal_maker.database.tables.Goal;

public interface GoalService {
    public Goal getGoalById(long id);
    public void addGoal(Goal goal);
    public void modifyGoal(Goal goal);
    public Goal getCurrentGoal(long userId);
    public void deleteGoal(long goalId);

}
