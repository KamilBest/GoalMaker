package goal_maker.database.dao.goal_dao;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Goal;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

@Repository
public class GoalDaoImpl implements GoalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;
    @Override
    public Goal getGoalById(long id) {
<<<<<<< HEAD
        String sqlSelect = "SELECT id_goal, name, id_category, value, number_of_days, picture_name FROM goal_maker.goal WHERE id_goal=" + id;
=======
        String sqlSelect = "SELECT id_goal, name, goal.id_category, value, number_of_days, picture_name FROM goal_maker.goal WHERE id_goal=" + id;
>>>>>>> 17949359f4646da708bcc4cd8ccf600e8fd9c37b
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

        //get next goal ID
        String getAddedGoalId="SELECT id_goal FROM goal_maker.goal ORDER BY id_goal DESC LIMIT 1";
        Integer lastId  = (Integer)entityManager.createNativeQuery(getAddedGoalId).getSingleResult();
        //current logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);

        //user update
        String sqlUpdate="UPDATE goal_maker.user_gm SET id_goal=? WHERE id_user=?";
        Query updateQuery=entityManager.createNativeQuery(sqlUpdate, GmUser.class);
        updateQuery.setParameter(1, lastId);
        updateQuery.setParameter(2, gmUser.getId());
        updateQuery.executeUpdate();



    }
}
