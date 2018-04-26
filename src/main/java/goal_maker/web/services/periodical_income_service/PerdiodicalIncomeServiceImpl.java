package goal_maker.web.services.periodical_income_service;

import goal_maker.database.dao.periodical_income_dao.PeriodicalIncomeDao;
import goal_maker.database.tables.PeriodicalIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("PeriodicalIncomeService")
public class PerdiodicalIncomeServiceImpl implements PeriodicalIncomeService {
    @Autowired
    PeriodicalIncomeDao periodicalIncomeDao;

    @Override
    public List<PeriodicalIncome> findAllPeriodicalIncomesForUser(long userFinancesId) {
        return periodicalIncomeDao.findAllPeriodicalIncomesForUser(userFinancesId);
    }

    @Override
    public void changeLastIncomeDate(long userFinancesId,Date newDate) {
        periodicalIncomeDao.changeLastIncomeDate(userFinancesId, newDate);
    }
}
