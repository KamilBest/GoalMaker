package goal_maker.database.dao.income_dao;

import goal_maker.database.tables.Income;

import java.util.List;

public interface IncomeDao {
    public List<Income> findAllUserIncomes(long id);
    public void addIncome(Income income);
    public List<Income> findLastUserIncomes(long id, long amount);
}
