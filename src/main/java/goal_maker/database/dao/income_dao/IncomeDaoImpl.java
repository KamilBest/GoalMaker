package goal_maker.database.dao.income_dao;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Income;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IncomeDaoImpl implements IncomeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;

    @Override
    public List<Income> findAllUserIncomes(long id) {
        String sqlSelect = "SELECT id_income, type, value, id_user_finances, date, name  FROM goal_maker.income WHERE id_user_finances=" + id;
        List<Income> incomes = entityManager.createNativeQuery(sqlSelect, Income.class).getResultList();
        return incomes;
    }


    @Transactional
    @Override
    public void addIncome(Income income) {
        String sqlInsert = "INSERT INTO goal_maker.income(type, value, id_user_finances, date, name) VALUES (?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, Income.class);
        query.setParameter(1, income.getType());
        query.setParameter(2, income.getValue());
        query.setParameter(3, income.getUser_finances().getId_user_finances());
        query.setParameter(4, income.getDate());
        query.setParameter(5, income.getName());
        query.executeUpdate();
    }

    @Override
    public List<Income> findTenLastUserIncomes(long id) {
        String sqlSelect = "SELECT id_income, type, value, id_user_finances, date, name  FROM goal_maker.income WHERE id_user_finances=" + id + "ORDER BY id_income DESC LIMIT 10";
        List<Income> lastFiveIncomes = entityManager.createNativeQuery(sqlSelect, Income.class).getResultList();
        return lastFiveIncomes;
    }
}
