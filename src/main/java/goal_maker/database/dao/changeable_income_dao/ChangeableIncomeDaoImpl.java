package goal_maker.database.dao.changeable_income_dao;

import goal_maker.database.tables.ChangeableIncome;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Maarcin on 2018-01-15.
 */
@Repository
public class ChangeableIncomeDaoImpl implements ChangeableIncomeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChangeableIncome> findAllByIncomeId(long incomeId) {
        String sqlSelect = "Select * From goal_maker.changeable_income WHERE id_income=" + incomeId;
        List<ChangeableIncome> changeableIncomeList = entityManager.createNativeQuery(sqlSelect, ChangeableIncome.class).getResultList();
        return changeableIncomeList;
    }

    @Override
    public ChangeableIncome getChangeableIncomeById(long changeableId) {
        String sqlSelect = "SELECT id_changeable_income, name, value, id_income FROM goal_maker.changeable_income WHERE id_changeable_income=" + changeableId;
        Query query = entityManager.createNativeQuery(sqlSelect, ChangeableIncome.class);
        ChangeableIncome changeableIncome = (ChangeableIncome) query.getSingleResult();
        return changeableIncome;
    }

    @Transactional
    @Override
    public void addChangeableIncome(ChangeableIncome changeableIncome) {
        String sqlInsert = "INSERT INTO goal_maker.changeable_income( name, value, id_income) VALUES (?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, ChangeableIncome.class);
        query.setParameter(1, changeableIncome.getName());
        query.setParameter(2, changeableIncome.getValue());
        query.setParameter(3, changeableIncome.getIncome().getId_income());
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void editChangeableIncome(ChangeableIncome changeableIncome) {
        String sqlInsert = "UPDATE INTO goal_maker.changeable_income( name, value, id_income) VALUES (?, ?, ?)";
        Query query = entityManager.createNativeQuery(sqlInsert, ChangeableIncome.class);
        query.setParameter(1, changeableIncome.getName());
        query.setParameter(2, changeableIncome.getValue());
        query.setParameter(3, changeableIncome.getIncome().getId_income());
        query.executeUpdate();
    }
}
