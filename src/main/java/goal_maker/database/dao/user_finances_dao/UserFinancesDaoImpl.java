package goal_maker.database.dao.user_finances_dao;

import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserFinancesDaoImpl implements UserFinancesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserFinances> findAll() {

        String sqlSelect = "SELECT id_user_finances, account_balance, current_state_to_goal FROM goal_maker.user_finances";
        List<UserFinances> userFinancesList = entityManager.createNativeQuery(sqlSelect, UserFinances.class).getResultList();
        return userFinancesList;
    }

    @Override
    public UserFinances getUserFinanceById(long id) {
        String sqlSelect = "SELECT id_user_finances, account_balance, current_state_to_goal FROM goal_maker.user_finances WHERE id_user_finances="+id;
        Query query = entityManager.createNativeQuery(sqlSelect, UserFinances.class);
        UserFinances userFinances=(UserFinances)query.getSingleResult();
        return userFinances;
    }

    @Transactional
    @Override
    public void addUserFinance(UserFinances userFinances) {
        String sqlInsert = "INSERT INTO goal_maker.user_finances(id_user_finances, account_balance, current_state_to_goal) VALUES (?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, UserFinances.class);
        query.setParameter(1, userFinances.getId_user_finances());
        query.setParameter(2, userFinances.getAccount_balance());
        query.setParameter(3, userFinances.getCurrent_state_to_goal());

        query.executeUpdate();

    }
    @Transactional
    @Override
    public void updateAccountBalance(UserFinances userFinances) {
        String sqlUpdate = "UPDATE goal_maker.user_finances SET account_balance=? WHERE id_user_finances=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, UserFinances.class);
        updateQuery.setParameter(1, userFinances.getAccount_balance());
        updateQuery.setParameter(2, userFinances.getId_user_finances());

        updateQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void updateCurrentStateToGoal(Income income) {
        String sqlUpdate= "UPDATE goal_maker.user_finances SET current_state_to_goal=? WHERE id_user_finances=?";
        Query updateQuery=entityManager.createNativeQuery(sqlUpdate, UserFinances.class);
        UserFinances userFinances=income.getUser_finances();

        updateQuery.setParameter(1, userFinances.getCurrent_state_to_goal()+income.getValue());
        updateQuery.setParameter(2, userFinances.getId_user_finances());
        updateQuery.executeUpdate();

    }
}




