package goal_maker.database.dao.income_dao;

import goal_maker.database.tables.Income;

import java.util.List;

public interface IncomeDao {
     List<Income> findAllUserIncomes(long id);
     void addIncome(Income income);
     void deleteIncome(Income income);
}
