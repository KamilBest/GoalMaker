package goal_maker.database.dao.constant_income_dao;

import goal_maker.database.tables.ConstantIncome;
import goal_maker.database.tables.Income;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ConstantIncomeDaoImpl implements ConstantIncomeDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Income> findAllByIncomeId(long incomeId) {
        String sqlSelect = "SELECT id_constant_income, name, value, id_income, how_many_days FROM goal_maker.constant_income WHERE id_income="+incomeId;
        List<Income> incomeList = entityManager.createNativeQuery(sqlSelect, Income.class).getResultList();
        return incomeList;
    }

    @Override
    public List<Income> getConstIncomeById(long constIncomeId) {
        String sqlSelect = "SELECT id_constant_income, name, value, id_income, how_many_days FROM goal_maker.constant_income; WHERE id_constant_income="+constIncomeId  ;
        List<Income> incomeList = entityManager.createNativeQuery(sqlSelect, Income.class).getResultList();
        return incomeList;

    }

    @Transactional
    @Override
    public void addConstantIncome(ConstantIncome constantIncome) {
        String sqlInsert = "INSERT INTO goal_maker.constant_income(name, value, id_income, how_many_days) VALUES (?, ?, ?, ?);";
        Query query = entityManager.createNativeQuery(sqlInsert, ConstantIncome.class);
        query.setParameter(1, constantIncome.getName());
        query.setParameter(2, constantIncome.getValue());
        query.setParameter(3, constantIncome.getIncome());
        query.setParameter(4, constantIncome.getHow_many_days());

        query.executeUpdate();

    }
    @Transactional
    @Override
    public void editConstantIncome(ConstantIncome constantIncome) {
        String sqlUpdate= "UPDATE goal_maker.constant_income SET name=?, value=?  WHERE id_constant_income=?";
        Query updateQuery=entityManager.createNativeQuery(sqlUpdate, ConstantIncome.class);
        updateQuery.setParameter(1, constantIncome.getName());
        updateQuery.setParameter(2, constantIncome.getValue());
        updateQuery.setParameter(3, constantIncome.getId_constant_income());

        updateQuery.executeUpdate();

    }
}
