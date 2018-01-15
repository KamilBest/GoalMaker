package goal_maker.web.services.constant_income_service;

import goal_maker.database.tables.ConstantIncome;
import goal_maker.database.tables.Income;

import java.util.List;

public interface ConstantIncomeService {
     List<Income> findAllByIncomeId(long incomeId);
     List<Income> getConstIncomeById(long constIncomeId);
     void addConstantIncome(ConstantIncome constantIncome);
     void editConstantIncome(ConstantIncome constantIncome);
}
