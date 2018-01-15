package goal_maker.web.services.changeable_income_service;

import goal_maker.database.dao.changeable_income_dao.ChangeableIncomeDao;
import goal_maker.database.tables.ChangeableIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Maarcin on 2018-01-15.
 */
@Service("ChangeableIncomeService")
public class ChangeableIncomeServiceImpl implements ChangeableIncomeService {
    @Autowired
    private ChangeableIncomeDao changeableIncomeDao;

    @Override
    public List<ChangeableIncome> findAllByIncomeId(long incomeId) {
        return changeableIncomeDao.findAllByIncomeId(incomeId);
    }

    @Override
    public ChangeableIncome getChangeableIncomeById(long changeableId) {
        return changeableIncomeDao.getChangeableIncomeById(changeableId);
    }

    @Override
    public void addChangeableIncome(ChangeableIncome changeableIncome) {
        changeableIncomeDao.addChangeableIncome(changeableIncome);
    }

    @Override
    public void editChangeableIncome(ChangeableIncome changeableIncome) {
        changeableIncomeDao.editChangeableIncome(changeableIncome);
    }
}
