package goal_maker.database.dao.income_dao;

import goal_maker.database.tables.Income;

import java.util.List;

public interface IncomeDao {
    public List<Income> findAllUserIncomes(long id);
    public Income getIncomeById(long incomeId);
    public void addIncome(Income income);
    public List<Income> findLastUserIncomes(long id, long amount);

    public void modifyIncome(Income income);
    public void deleteIncome(long incomeId);

}
