package goal_maker.database.dao.changeable_income_dao;

import goal_maker.database.tables.ChangeableIncome;

import java.util.List;

/**
 * Created by Maarcin on 2018-01-15.
 */
public interface ChangeableIncomeDao {
    public List<ChangeableIncome> findAllByIncomeId(long incomeId);
    public ChangeableIncome getChangeableIncomeById(long changeableId);
    public void addChangeableIncome(ChangeableIncome changeableIncome);
    public void editChangeableIncome(ChangeableIncome changeableIncome);
}
