package goal_maker.web.services.goal_service;

import goal_maker.database.tables.Goal;

import java.util.List;

public interface GoalService {
    public Goal getGoalById(long id);
    public List<Goal> getGoalsByState(long idGoalState);

    public void addGoal(Goal goal);
    public void modifyGoal(Goal goal);
    public Goal getCurrentGoal(long userId);
    public void deleteGoal(long goalId);
    public void changeGoalState(long goalId, long idGoalState);
    Goal getLastRealisedGoalByUserId(long idGoalState, long userId);
    List<Goal> getGoalsByStateAndUserId(long idGoalState, long userId);
}
