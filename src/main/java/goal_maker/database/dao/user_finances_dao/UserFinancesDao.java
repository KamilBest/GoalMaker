package goal_maker.database.dao.user_finances_dao;

import goal_maker.database.tables.UserFinances;


import java.util.List;

public interface UserFinancesDao {

    List<UserFinances> findAll();
    UserFinances getUserFinanceById(long id);
    void addUserFinance(UserFinances userFinances);
    void updateAccountBalance(UserFinances userFinances);
    void updateCurrentStateToGoal(UserFinances userFinances);
    void updateRealAccountBalance(Long userFinancesId, Long newRealAccountBalance);

}
