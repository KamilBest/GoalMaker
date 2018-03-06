package goal_maker.web.services.expenses_service;

import goal_maker.database.dao.expenses_dao.ExpensesDao;
import goal_maker.database.tables.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ExpensesService")
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private ExpensesDao expensesDao;

    @Override
    public List<Expenses> findAllUserExpenses(long id) {
        return expensesDao.findAllUserExpenses(id);
    }

    @Override
    public Expenses getExpenseById(long expenseId) {
        return expensesDao.getExpenseById(expenseId);
    }

    @Override
    public void addExpenses(Expenses expenses) {
        expensesDao.addExpenses(expenses);
    }

    @Override
    public List<Expenses> findLastUserExpenses(long id, long amount) {
        return expensesDao.findLastUserExpenses(id, amount);
    }

    @Override
    public void modifyExpense(Expenses expenses) {
        expensesDao.modifyExpense(expenses);
    }

    @Override
    public void deleteExpense(long expenseId) {
        expensesDao.deleteExpense(expenseId);
    }
}
