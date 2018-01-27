package goal_maker.web.services.income_service;

import goal_maker.database.tables.Income;

import java.util.List;

public interface IncomeService {
    public List<Income> findAllUserIncomes(long id);
    public void addIncome(Income income);
    public List<Income> findLastUserIncomes(long id, long amount);
}
