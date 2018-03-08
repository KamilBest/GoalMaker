package goal_maker.web.services.user_finances_service;

import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;
import goal_maker.database.tables.views.UserFinancesView;

import java.util.List;

public interface UserFinancesService {
    List<UserFinances> findAll();

    UserFinances getUserFinanceById(long id);

    void addUserFinance(UserFinances userFinances);

    void updateAccountBalance(UserFinances userFinances, long value, boolean addOrSubtract);

    void updateGoalBalance(Income income);
    void resetCurrentStateToGoal(long userFinancesId);
     long nextId();

    //USER FINANCES VIEW
    UserFinancesView getUserFinanceViewById(long id);
}
