package goal_maker.web.utils;

import java.util.TimerTask;

//https://stackoverflow.com/questions/11432498/how-to-call-a-thread-to-run-on-specific-time-in-java
public class PeriodicalIncomeTask extends TimerTask {
    Thread periodicalIncomeThread;

    PeriodicalIncomeTask (Thread t){
        this.periodicalIncomeThread=t;
    }

    @Override
    public void run() {
        periodicalIncomeThread.start();
    }
}
