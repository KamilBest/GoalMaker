package goal_maker.database.dao.expenses_dao;

import goal_maker.database.tables.Expenses;

import java.util.List;

public interface ExpensesDao {
    List<Expenses> findAllUserExpenses(long id);
   void addExpenses(Expenses income);
}
