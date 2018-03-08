package goal_maker.database.dao.user_finances_dao;

import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;


import java.util.List;

public interface UserFinancesDao {

    List<UserFinances> findAll();
    UserFinances getUserFinanceById(long id);
    void addUserFinance(UserFinances userFinances);
    void updateAccountBalance(UserFinances userFinances, long value, boolean addOrSubtract);
    void updateCurrentStateToGoal(Income income);
    void resetCurrentStateToGoal(long userFinancesId);
    long nextId();
    void updateRealAccountBalance(Long userFinancesId, Long newRealAccountBalance);

}
