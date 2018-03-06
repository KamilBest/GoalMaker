package goal_maker.web.services.user_finances_service;


import goal_maker.database.dao.user_finances_dao.UserFinancesDao;
import goal_maker.database.tables.Income;
import goal_maker.database.tables.UserFinances;
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
    public void updateAccountBalance(UserFinances userFinances, long value,boolean addOrSubtract) {
        userFinancesDao.updateAccountBalance(userFinances, value, addOrSubtract);
    }

    @Override
    public void updateCurrentStateToGoal(Income income) {userFinancesDao.updateCurrentStateToGoal(income);
    }

    @Override
    public void resetCurrentStateToGoal(long userFinancesId) {
        userFinancesDao.resetCurrentStateToGoal(userFinancesId);
    }

    @Override
    public long nextId() {
       return userFinancesDao.nextId();
    }
}
