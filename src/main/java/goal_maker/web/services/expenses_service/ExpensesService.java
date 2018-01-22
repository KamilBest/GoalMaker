package goal_maker.web.services.expenses_service;

import goal_maker.database.tables.Expenses;

import java.util.List;

public interface ExpensesService {
     List<Expenses> findAllUserExpenses(long id);
     void addExpenses(Expenses income);
}
