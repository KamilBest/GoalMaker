package goal_maker.web.services.income_service;

import goal_maker.database.tables.Income;

import java.util.List;

public interface IncomeService {
     List<Income> findAllUserIncomes(long id);
     void addIncome(Income income);
     void deleteIncome(Income income);
}
