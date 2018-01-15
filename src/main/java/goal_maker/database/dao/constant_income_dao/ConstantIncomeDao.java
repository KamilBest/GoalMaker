package goal_maker.database.dao.constant_income_dao;

import goal_maker.database.tables.ConstantIncome;
import goal_maker.database.tables.Income;

import java.util.List;

public interface ConstantIncomeDao {
     List<Income> findAllByIncomeId(long incomeId);
     List<Income> getConstIncomeById(long constIncomeId);
     void addConstantIncome(ConstantIncome constantIncome);
     void editConstantIncome(ConstantIncome constantIncome);
}
