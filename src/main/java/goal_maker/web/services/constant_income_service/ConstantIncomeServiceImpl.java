package goal_maker.web.services.constant_income_service;

import goal_maker.database.dao.constant_income_dao.ConstantIncomeDao;
import goal_maker.database.tables.ConstantIncome;
import goal_maker.database.tables.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ConstantIncomeService")
public class ConstantIncomeServiceImpl implements ConstantIncomeService{

    @Autowired
    private ConstantIncomeDao constantIncomeDao;

    @Override
    public List<Income> findAllByIncomeId(long incomeId) {
        return constantIncomeDao.findAllByIncomeId(incomeId);
    }

    @Override
    public List<Income> getConstIncomeById(long constIncomeId) {
        return constantIncomeDao.getConstIncomeById(constIncomeId);
    }

    @Override
    public void addConstantIncome(ConstantIncome constantIncome) {
        constantIncomeDao.addConstantIncome(constantIncome);
    }

    @Override
    public void editConstantIncome(ConstantIncome constantIncome) {
        constantIncomeDao.editConstantIncome(constantIncome);
    }
}
