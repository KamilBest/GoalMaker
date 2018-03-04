package goal_maker.database.dao.expenses_dao;

import goal_maker.database.tables.Expenses;

import java.util.List;

public interface ExpensesDao {
    List<Expenses> findAllUserExpenses(long id);
    public Expenses getExpenseById(long expenseId);

    void addExpenses(Expenses expense);
    List<Expenses> findLastUserExpenses(long id, long amount);

    public void modifyExpense(Expenses expenses);
    public void deleteExpense(long expenseId);
}
