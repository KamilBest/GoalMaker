package goal_maker.web.services.periodical_income_service;

import goal_maker.database.tables.PeriodicalIncome;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface PeriodicalIncomeService {
    List<PeriodicalIncome> findAllPeriodicalIncomesForUser(long userFinancesId);
    void changeLastIncomeDate(long userFinancesId, Date newDate);

}
