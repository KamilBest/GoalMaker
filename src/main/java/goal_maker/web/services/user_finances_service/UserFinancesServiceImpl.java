package goal_maker.web.services.user_finances_service;


import goal_maker.database.dao.user_finances_dao.UserFinancesDao;
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
    public void updateAccountBalance(UserFinances userFinances) {userFinancesDao.updateAccountBalance(userFinances);
    }

    @Override
    public void updateCurrentStateToGoal(UserFinances userFinances) {userFinancesDao.updateCurrentStateToGoal(userFinances);
    }

    @Override
    public void updateRealAccountBalance(Long userFinancesId, Long newRealAccountBalance) {
        userFinancesDao.updateRealAccountBalance(userFinancesId,newRealAccountBalance);
    }
}
