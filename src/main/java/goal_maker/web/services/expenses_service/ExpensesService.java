package goal_maker.web.services.expenses_service;

import goal_maker.database.tables.Expenses;

import java.util.List;

public interface ExpensesService {
     List<Expenses> findAllUserExpenses(long id);
     public Expenses getExpenseById(long expenseId);

     void addExpenses(Expenses expense);
     List<Expenses> findLastUserExpenses(long id, long amount);

     public void modifyExpense(Expenses expenses);
     public void deleteExpense(long expenseId);
}
