package goal_maker.database.dao.goal_dao;

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
        String sqlSelect = "SELECT id_goal, name, goal.id_category, value, number_of_days, picture_name FROM goal_maker.goal WHERE id_goal=" + id;
        Query query = entityManager.createNativeQuery(sqlSelect, Goal.class);
        Goal goal = (Goal) query.getSingleResult();
        return goal;
    }

    @Transactional
    @Override
    public void addGoal(Goal goal) {
        String sqlInsert = "INSERT INTO goal_maker.goal( name, id_category, value) VALUES (?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, Goal.class);
        query.setParameter(1, goal.getName());
        query.setParameter(2, goal.getCategory().getId_category());
        query.setParameter(3, goal.getValue());
        query.executeUpdate();
    }
}
