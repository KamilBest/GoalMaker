package goal_maker.web.services.user_finances_service;


import goal_maker.database.dao.user_finances_dao.UserFinancesDao;
import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;
import goal_maker.database.tables.views.UserFinancesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserFinancesService")
public class UserFinancesServiceImpl implements UserFinancesService {

    @Autowired
    private UserFinancesDao userFinancesDao;

    @Override
    public List<UserFinances> findAll() {  return userFinancesDao.findAll();
    }

    @Override
    public UserFinances getUserFinanceById(long id) {  return userFinancesDao.getUserFinanceById(id);
    }

    @Override
    public void addUserFinance(UserFinances userFinances) {  userFinancesDao.addUserFinance(userFinances);
    }

    @Override
    public void updateRealAccountBalance(UserFinances userFinances, long value, boolean addOrSubtract) {
        userFinancesDao.updateRealAccountBalance(userFinances, value, addOrSubtract);
    }

    @Override
    public void updateGoalBalance(Income income) {userFinancesDao.updateGoalBalance(income);
    }

    @Override
    public void updateGoalBalance(long value, UserFinances userFinances) {
        userFinancesDao.updateGoalBalance(value,userFinances);
    }

    @Override
    public void removeGoalValue(long value, long userFinancesId) {
        userFinancesDao.removeGoalValue(value, userFinancesId);
    }

    @Override
    public long nextId() {
       return userFinancesDao.nextId();
    }

    @Override
    public UserFinancesView getUserFinanceViewById(long id) {
        return userFinancesDao.getUserFinanceViewById(id);
    }
}
