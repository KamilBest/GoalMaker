package goal_maker.web.services.expenses_service;

import goal_maker.database.dao.expenses_dao.ExpensesDao;
import goal_maker.database.tables.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ExpensesService")
public class ExpensesServiceImpl implements ExpensesService{

    @Autowired
   private ExpensesDao expensesDao;
    @Override
    public List<Expenses> findAllUserExpenses(long id) {
        return  expensesDao.findAllUserExpenses(id);
    }

    @Override
    public void addExpenses(Expenses income) {
        expensesDao.addExpenses(income);
    }
}
