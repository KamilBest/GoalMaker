package goal_maker.database.dao.user_finances_dao;

import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;
import goal_maker.database.tables.views.UserFinancesView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class UserFinancesDaoImpl implements UserFinancesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserFinances> findAll() {

        String sqlSelect = "SELECT * FROM goal_maker.user_finances";
        List<UserFinances> userFinancesList = entityManager.createNativeQuery(sqlSelect, UserFinances.class).getResultList();
        return userFinancesList;
    }

    @Override
    public UserFinances getUserFinanceById(long id) {
        String sqlSelect = "SELECT * FROM goal_maker.user_finances WHERE id_user_finances=" + id;
        Query query = entityManager.createNativeQuery(sqlSelect, UserFinances.class);
        UserFinances userFinances = (UserFinances) query.getSingleResult();
        return userFinances;
    }

    @Transactional
    @Override
    public void addUserFinance(UserFinances userFinances) {
        String sqlInsert = "INSERT INTO goal_maker.user_finances(real_account_balance, goal_balance) VALUES (?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, UserFinances.class);
        query.setParameter(1, userFinances.getReal_account_balance());
        query.setParameter(2, userFinances.getGoal_balance());
        query.executeUpdate();

    }

    /**
     * @param userFinances
     * @param value
     * @param isIncome     true - add / false-subtract
     */
    @Transactional
    @Override
    public void updateRealAccountBalance(UserFinances userFinances, long value, boolean isIncome) {
        String sqlUpdate = "UPDATE goal_maker.user_finances SET real_account_balance=? WHERE id_user_finances=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, UserFinances.class);
        if (isIncome) {
            updateQuery.setParameter(1, userFinances.getReal_account_balance() + value);
        } else {
            updateQuery.setParameter(1, userFinances.getReal_account_balance() - value);
        }
        updateQuery.setParameter(2, userFinances.getId_user_finances());

        updateQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void updateGoalBalance(Income income) {
        String sqlUpdate = "UPDATE goal_maker.user_finances SET goal_balance=? WHERE id_user_finances=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, UserFinances.class);
        UserFinances userFinances = income.getUser_finances();

        updateQuery.setParameter(1, userFinances.getGoal_balance() + income.getValue());
        updateQuery.setParameter(2, userFinances.getId_user_finances());
        updateQuery.executeUpdate();

    }

    @Transactional
    @Override
    public void updateGoalBalance(long value, UserFinances userFinances) {
        String sqlUpdate = "UPDATE goal_maker.user_finances SET goal_balance=? WHERE id_user_finances=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, UserFinances.class);

        updateQuery.setParameter(1, value);
        updateQuery.setParameter(2, userFinances.getId_user_finances());
        updateQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void resetCurrentStateToGoal(long userFinancesId) {
        String sqlUpdate = "UPDATE goal_maker.user_finances SET goal_balance=0 WHERE id_user_finances=?";
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, UserFinances.class);
        updateQuery.setParameter(1, userFinancesId);
        updateQuery.executeUpdate();
    }

    @Override
    public long nextId() {
        String sqlSelect = "SELECT (last_value + 1) AS new_id FROM goal_maker.user_finances_id_user_finances_seq";
        BigInteger newID = (BigInteger) entityManager.createNativeQuery(sqlSelect).getSingleResult();
        return newID.longValue();
    }

    @Override
    public UserFinancesView getUserFinanceViewById(long id) {
        String sqlSelect = "SELECT * FROM goal_maker.user_finances_view WHERE id_user_finances=" + id;
        Query query = entityManager.createNativeQuery(sqlSelect, UserFinancesView.class);
        UserFinancesView userFinancesView = (UserFinancesView) query.getSingleResult();
        return userFinancesView;
    }
}




