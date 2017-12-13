package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Goal;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class GoalDaoImpl implements GoalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Goal getGoalById(long id) {
        String sqlSelect = "SELECT id_goal, name, category, value, number_of_days, picture FROM goal_maker.goal WHERE id_goal=" + id;
        Query query = entityManager.createNativeQuery(sqlSelect, Goal.class);
        Goal goal = (Goal) query.getSingleResult();
        return goal;
    }
    @Transactional
    @Override
    public void addGoal(Goal goal) {
        String sqlInsert = "INSERT INTO goal_maker.goal(id_goal, name, category, value, number_of_days, picture) VALUES (?, ?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, Goal.class);
        query.setParameter(1, goal.getId_goal());
        query.setParameter(2, goal.getName());
        query.setParameter(3, goal.getCategory());
        query.setParameter(4, goal.getValue());
        query.setParameter(5, goal.getNumber_of_days());
        query.setParameter(6, goal.getPicture());
        query.executeUpdate();
    }
}
