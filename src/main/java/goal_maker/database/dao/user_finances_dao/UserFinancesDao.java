package goal_maker.database.dao.user_finances_dao;

import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;
import goal_maker.database.tables.views.UserFinancesView;


import java.util.List;

public interface UserFinancesDao {

    List<UserFinances> findAll();
    UserFinances getUserFinanceById(long id);
    void addUserFinance(UserFinances userFinances);
    void updateRealAccountBalance(UserFinances userFinances, long value, boolean addOrSubtract);
    void updateGoalBalance(Income income);
    void updateGoalBalance(long value, UserFinances userFinances);

    void removeGoalValue(long value,long userFinancesId);
    long nextId();

    //USER FINANCES VIEW
    UserFinancesView getUserFinanceViewById(long id);


}
