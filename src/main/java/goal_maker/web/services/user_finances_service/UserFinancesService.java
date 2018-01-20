package goal_maker.web.services.user_finances_service;

import goal_maker.database.tables.UserFinances;

import java.util.List;

public interface UserFinancesService {
    List<UserFinances> findAll();
    UserFinances getUserFinanceById(long id);
    void addUserFinance(UserFinances userFinances);
}
