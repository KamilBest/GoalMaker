package goal_maker.web.controller;

import goal_maker.database.dao.goal_dao.GoalDao;
import goal_maker.database.tables.*;
import goal_maker.database.wrapper.IncomeAndExpense;
import goal_maker.web.services.expenses_service.ExpensesService;
import goal_maker.web.services.goal_service.GoalService;
import goal_maker.web.services.income_service.IncomeService;
import goal_maker.web.services.user_finances_service.UserFinancesService;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    GoalDao goalDao;

    @Autowired
    UserService userService;
    @Autowired
    GoalService goalService;
    @Autowired
    IncomeService incomeService;

    @Autowired
    ExpensesService expensesService;

    @Autowired
    UserFinancesService userFinancesService;
    private List<Goal> realisedGoals = new ArrayList<>();

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String showFrontPage(Model model) {
        model.addAttribute("location", "dashboard");

        //get current logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //get logged in login
        GmUser gmUser = userService.getUserByLogin(login);

        //Handle reaching goal
        final long REALISED = 1;
        //Check if this user has a goal, if doesn't show only button to add goal
        Goal currentGoal = goalDao.getCurrentGoal(gmUser.getId());


        model.addAttribute("realisedGoals", goalService.getGoalsByState(REALISED));

        //get this user finances, to display his incomes and expenses
        model.addAttribute("currentUserFinances", gmUser.getUserFinances());
        long currentUserFinancesId = gmUser.getUserFinances().getId_user_finances();

        //PROGRESS BAR

        //display records of given amount
        final int amountOfIncomesAndExpenses = 10;
        List<Income> incomesList = incomeService.findLastUserIncomes(currentUserFinancesId, amountOfIncomesAndExpenses);
        List<Expenses> expensesList = expensesService.findLastUserExpenses(currentUserFinancesId, amountOfIncomesAndExpenses);
        model.addAttribute("lastIncomeList", incomesList);
        model.addAttribute("lastExpensesList", expensesList);

        if ((incomesList != null || !incomesList.isEmpty()) && (expensesList != null || !expensesList.isEmpty())) {
            List<IncomeAndExpense> incomeAndExpenseList = loadIncomesAndExpensesList(incomesList, expensesList);
            if (incomeAndExpenseList != null || !incomeAndExpenseList.isEmpty()) {
                Collections.sort(incomeAndExpenseList);
                if (incomeAndExpenseList.size() > amountOfIncomesAndExpenses)
                    incomeAndExpenseList = decreaseListToGivenAmount(incomeAndExpenseList, amountOfIncomesAndExpenses);
                model.addAttribute("incomesAndExpensesTogether", incomeAndExpenseList);
            }
        }


        return "index";
    }

    /**
     * Loads incomes and expenses to one list
     *
     * @param incomesList
     * @param expensesList
     * @return
     */
    private List<IncomeAndExpense> loadIncomesAndExpensesList(List<Income> incomesList, List<Expenses> expensesList) {
        List<IncomeAndExpense> incomeAndExpenseList = new ArrayList<>();

        for (int i = 0; i < incomesList.size(); i++) {
            Income income = incomesList.get(i);
            IncomeAndExpense incomeAndExpense = new IncomeAndExpense(income.getId_income(), true, income.getType(), income.getValue(), income.getName(), income.getDate());
            incomeAndExpenseList.add(incomeAndExpense);
        }

        for (int i = 0; i < expensesList.size(); i++) {
            Expenses expenses = expensesList.get(i);
            IncomeAndExpense incomeAndExpense = new IncomeAndExpense(expenses.getIdExpenses(), false, expenses.getType(), expenses.getValue(), expenses.getName(), expenses.getDate());
            incomeAndExpenseList.add(incomeAndExpense);
        }

        return incomeAndExpenseList;
    }

    /**
     * Decrease list to given amount of records
     *
     * @param incomeAndExpenseList
     * @param amountOfIncomesAndExpenses
     * @return
     */
    private List<IncomeAndExpense> decreaseListToGivenAmount(List<IncomeAndExpense> incomeAndExpenseList, int amountOfIncomesAndExpenses) {
        List<IncomeAndExpense> decreasedList = new ArrayList<>();
        for (int i = 0; i < amountOfIncomesAndExpenses; i++) {
            decreasedList.add(incomeAndExpenseList.get(i));
        }
        return decreasedList;
    }

    /**
     * Calculates collected funds of current goal percentage value
     *
     * @param gmUser - current logged user
     * @return
     */
    private long calculateCurrentGoalPercentageValue(GmUser gmUser, Goal currentGoal) {
        long goalValue = currentGoal.getValue();
        return (gmUser.getUserFinances().getGoal_balance() * 100) / goalValue;
    }

    /**
     * Method checks if goal is achieved
     *
     * @return returns true if achieved
     */
    private boolean isGoalAchieved(GmUser gmUser, Goal currentGoal) {
        UserFinances userFinances = gmUser.getUserFinances();
        if (userFinances.getGoal_balance() >= currentGoal.getValue()) {
            final long REALISED = 1;
            goalService.changeGoalState(currentGoal.getId_goal(), REALISED);
            userFinancesService.removeGoalValue(userFinances.getGoal_balance()-currentGoal.getValue(), userFinances.getId_user_finances());
            return true;
        }
        return false;
    }

}
