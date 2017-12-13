package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Goal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class GoalDaoImpl implements GoalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Goal getGoalById(long id) {
        String sqlSelect = "SELECT id_goal, name, category, value, numbers_of_days, picture FROM goal_maker.goal WHERE id_goal=" + id;
        Query query = entityManager.createNativeQuery(sqlSelect, Goal.class);
        Goal goal = (Goal) query.getSingleResult();
        return goal;
    }
}
