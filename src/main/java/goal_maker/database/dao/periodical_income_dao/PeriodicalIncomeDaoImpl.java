package goal_maker.database.dao.periodical_income_dao;

import goal_maker.database.tables.PeriodicalIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class PeriodicalIncomeDaoImpl implements PeriodicalIncomeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PeriodicalIncome> findAllPeriodicalIncomesForUser(long userFinancesId) {
        String sqlSelect = "SELECT * FROM goal_maker.periodical_income WHERE id_user_finances=" + userFinancesId;
        List<PeriodicalIncome> periodicalIncomes = entityManager.createNativeQuery(sqlSelect, PeriodicalIncome.class).getResultList();

        return periodicalIncomes;
    }


    @Transactional
    @Override
    public void changeLastIncomeDate(long userFinancesId, Date newDate) {
//        String sqlUpdate = "UPDATE goal_maker.periodical_income SET last_income_date=" + newDate +
//                " WHERE id_user_finances= " + userFinancesId;
//        Query updateQuery = entityManager.createNativeQuery(sqlUpdate, PeriodicalIncome.class);
//        updateQuery.executeUpdate();

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaUpdate<PeriodicalIncome> criteriaUpdate = criteriaBuilder
                .createCriteriaUpdate(PeriodicalIncome.class);
        Root root = criteriaUpdate.from(PeriodicalIncome.class);

        criteriaUpdate.set("last_income_date",
                newDate == null ? null : newDate);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("idPerdiodicalIncome"),
                userFinancesId));
        // perform update
        this.entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
}
