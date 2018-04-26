package goal_maker.database.dao.periodical_income_dao;

import goal_maker.database.tables.PeriodicalIncome;

import java.util.Date;
import java.util.List;

public interface PeriodicalIncomeDao {
    List<PeriodicalIncome> findAllPeriodicalIncomesForUser(long userFinancesId);

    void changeLastIncomeDate(long userFinancesId, Date newDate);
}
