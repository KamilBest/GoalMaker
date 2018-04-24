package goal_maker.web.controller;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.Income;
import goal_maker.database.tables.PeriodicalIncome;
import goal_maker.web.services.income_service.IncomeService;
import goal_maker.web.services.periodical_income_service.PeriodicalIncomeService;
import goal_maker.web.services.user_service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.HOURS;


@Component
public class PeriodicalIncomeController  implements
        ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalIncomeController.class);

    private  List<GmUser> users;

    @Autowired
    UserService userService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    PeriodicalIncomeService periodicalIncomeService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {

        users = userService.getUsersList();

        final List<Runnable> jobs = new ArrayList<Runnable>();

        jobs.add(addPeriodicalIncome());

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(jobs.size());

        for(Runnable job : jobs){

            scheduler.scheduleWithFixedDelay(job, 6, 6, HOURS);

        }

    }

    private Runnable addPeriodicalIncome(){

        final Runnable job = new Runnable() {
            public void run() {
                LOG.debug("== ADDED PERIODICAL INCOMES == ", 1);
                final String CONSTANT_TYPE = "stały";
                for(GmUser gmUser : users)
                {
                    long userFinancesId = gmUser.getUserFinances().getId_user_finances();
                    List<PeriodicalIncome> periodicalIncomeList=periodicalIncomeService.findAllPeriodicalIncomesForUser(userFinancesId);
                    for(PeriodicalIncome periodicalIncome: periodicalIncomeList)
                    {
                        Calendar c = Calendar.getInstance();
                        if(periodicalIncome.getLastIncomeDate()!=null)
                            c.setTime(periodicalIncome.getLastIncomeDate());
                        else
                            c.setTime(periodicalIncome.getInitDate());
                        c.add(Calendar.DATE, (int)periodicalIncome.getPeriodLength());

                        Date nextIncomeDateForCurrentPeriodicalIncome = c.getTime();
                        Date currentDate = new Date();

                        if(nextIncomeDateForCurrentPeriodicalIncome.after(currentDate) || nextIncomeDateForCurrentPeriodicalIncome.equals(currentDate))
                        {
                            //change last income date to next Income date for current period
                            periodicalIncomeService.changeLastIncomeDate(userFinancesId, nextIncomeDateForCurrentPeriodicalIncome);
                            incomeService.addIncome(new Income(CONSTANT_TYPE, periodicalIncome.getValue(), gmUser.getUserFinances(), new Timestamp(System.currentTimeMillis()), periodicalIncome.getName()));
                        }
                    }
                }
            }
        };

        return job;

    }
}
