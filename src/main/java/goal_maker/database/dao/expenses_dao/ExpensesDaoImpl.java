package goal_maker.database.dao.expenses_dao;

import goal_maker.database.tables.Expenses;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ExpensesDaoImpl implements ExpensesDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;

    @Override
    public List<Expenses> findAllUserExpenses(long id) {
        String sqlSelect = "SELECT id_expenses, type, value, id_user_finances, date, name FROM goal_maker.expenses WHERE id_user_finances=" + id;
        List<Expenses> expensesList = entityManager.createNativeQuery(sqlSelect, Expenses.class).getResultList();
        return expensesList;
    }

    @Override
    public Expenses getExpenseById(long expenseId) {
        String sqlSelect = "SELECT * FROM goal_maker.expenses WHERE id_expenses = " + expenseId;
        Query query = entityManager.createNativeQuery(sqlSelect, Expenses.class);
        Expenses expenses = (Expenses) query.getSingleResult();
        return expenses;
    }

    @Transactional
    @Override
    public void addExpenses(Expenses expenses) {
        String sqlInsert = "INSERT INTO goal_maker.expenses(type, value, id_user_finances, date, name) VALUES (?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, Expenses.class);
        query.setParameter(1, expenses.getType());
        query.setParameter(2, expenses.getValue());
        query.setParameter(3, expenses.getUserFinances().getId_user_finances());
        query.setParameter(4, expenses.getDate());
        query.setParameter(5, expenses.getName());
        query.executeUpdate();
    }

    @Override
    public List<Expenses> findLastUserExpenses(long id, long amount) {
        String sqlSelect = "SELECT id_expenses, type, value, id_user_finances, date, name FROM goal_maker.expenses WHERE id_user_finances=" + id + "ORDER BY id_expenses DESC LIMIT " + amount;
        List<Expenses> lastExpenses = entityManager.createNativeQuery(sqlSelect, Expenses.class).getResultList();
        return lastExpenses;
    }

    @Transactional
    @Override
    public void modifyExpense(Expenses expenses) {
        String sqlUpdate = "UPDATE goal_maker.expenses SET type=?, value=?, name=? WHERE id_expenses="+expenses.getIdExpenses();
        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, Expenses.class);
        updateQuery.setParameter(1, expenses.getType());
        updateQuery.setParameter(2,expenses.getValue());
        updateQuery.setParameter(3, expenses.getName());
        updateQuery.executeUpdate();
    }

    @Transactional
    @Override
    public void deleteExpense(long expenseId) {
        String sqlDelete = "DELETE FROM goal_maker.expenses WHERE id_expenses="+expenseId;

        Query deleteQuery = entityManager.createNativeQuery(sqlDelete, Expenses.class);
        deleteQuery.executeUpdate();
    }
}
