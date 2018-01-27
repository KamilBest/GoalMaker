package goal_maker.web.services.income_service;

import goal_maker.database.dao.income_dao.IncomeDao;
import goal_maker.database.tables.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IncomeService")
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    IncomeDao incomeDao;

    @Override
    public List<Income> findAllUserIncomes(long id) {
        return incomeDao.findAllUserIncomes(id);
    }

    @Override
    public void addIncome(Income income) {
            incomeDao.addIncome(income);
    }

    @Override
    public List<Income> findFiveLastUserIncomes(long id) {
        return incomeDao.findFiveLastUserIncomes(id);
    }
}
