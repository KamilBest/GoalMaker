package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Goal;
import goal_maker.web.controller.DashboardController;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GoalDaoImpl implements GoalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;

    @Override
    public Goal getGoalById(long id) {
        String sqlSelect = "SELECT * FROM goal_maker.goal WHERE id_goal=" + id;
        Query query = entityManager.createNativeQuery(sqlSelect, Goal.class);
        Goal goal = (Goal) query.getSingleResult();
        return goal;
    }

    @Override
    public List<Goal> getGoalsByState(long idGoalState) {
        String sqlSelect = "SELECT * FROM goal_maker.goal WHERE id_goal_state= "+idGoalState;
        List<Goal> goals = entityManager.createNativeQuery(sqlSelect, Goal.class).getResultList();
        return goals;
    }

    @Override
    public Goal getLastRealisedGoal(long idGoalState) {
        String sqlSelect = "SELECT * FROM goal_maker.goal WHERE id_goal_state="+idGoalState +" ORDER BY id_goal DESC LIMIT 1";
        Query query = entityManager.createNativeQuery(sqlSelect, Goal.class);
        Goal goal = (Goal) query.getSingleResult();
        return null;
    }

    @Transactional
    @Override
    public void addGoal(Goal goal) {
        String sqlInsert = "INSERT INTO goal_maker.goal( name, id_category, value, id_user) VALUES (?, ?, ?,?)";
        Query query = entityManager.createNativeQuery(sqlInsert, Goal.class);
        query.setParameter(1, goal.getName());
        query.setParameter(2, goal.getCategory().getId_category());
        query.setParameter(3, goal.getValue());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);

        query.setParameter(4, gmUser.getId());
        query.executeUpdate();

   /*     //get next goal ID
        String getAddedGoalId = "SELECT id_goal FROM goal_maker.goal ORDER BY id_goal DESC LIMIT 1";
        Integer lastId = (Integer) entityManager.createNativeQuery(getAddedGoalId).getSingleResult();*/
    }

    @Transactional
    @Override
    public void modifyGoal(Goal goal) {
        //Goal update
        String sqlUpdateGoal = "UPDATE goal_maker.goal SET name=?, value=?, id_category=? WHERE id_goal=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdateGoal, Goal.class);
        updateQuery.setParameter(1, goal.getName());
        updateQuery.setParameter(2, goal.getValue());
        updateQuery.setParameter(3, goal.getCategory().getId_category());
        updateQuery.setParameter(4, goal.getId_goal());
        updateQuery.executeUpdate();
    }

    @Override
    public Goal getCurrentGoal(long userId) {
        String sqlSelect = "SELECT name, value, number_of_days, picture_name, id_goal, id_category, id_user, id_goal_state" +
                "  FROM goal_maker.goal WHERE id_user  = " + userId + "AND id_goal_state = 2";
        List<Goal> goalList = entityManager.createNativeQuery(sqlSelect, Goal.class).getResultList();
      //  Query query = entityManager.createNativeQuery(sqlSelect, Goal.class);
     //   Goal goal = (Goal) query.getSingleResult();
        if(goalList.isEmpty() || goalList==null)
            return null;
        return goalList.get(0);

     /*   String sqlSelect = "SELECT id_income, type, value, id_user_finances, date, name  FROM goal_maker.income WHERE id_user_finances=" + id;
        List<Income> incomes = entityManager.createNativeQuery(sqlSelect, Income.class).getResultList();
        return goal;*/
    }

    @Transactional
    @Override
    public void deleteGoal(long goalId) {

        String sqlUpdate = "UPDATE goal_maker.goal SET id_goal_state = 3 WHERE id_goal=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, GmUser.class);
        updateQuery.setParameter(1, goalId);
        updateQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void changeGoalState(long goalId, long idGoalState) {
        String sqlUpdateGoal = "UPDATE goal_maker.goal SET id_goal_state=? WHERE id_goal=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdateGoal, Goal.class);
        updateQuery.setParameter(1,idGoalState);
        updateQuery.setParameter(2, goalId);
        updateQuery.executeUpdate();
    }
    @Override
    public List<Goal> getGoalsByStateAndUserId(long idGoalState, long userId){
        String sqlSelect = "SELECT * FROM goal_maker.goal WHERE id_goal_state= "+idGoalState + " AND id_user="+userId+" ORDER BY id_goal DESC";
        List<Goal> goals = entityManager.createNativeQuery(sqlSelect, Goal.class).getResultList();
        return goals;
    }
}
