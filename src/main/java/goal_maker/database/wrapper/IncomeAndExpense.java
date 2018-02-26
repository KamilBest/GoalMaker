package goal_maker.database.wrapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class IncomeAndExpense implements Comparable {

    private long id;

    private boolean isIncome;

    private String type;

    private long value;

    private String name;

    private Timestamp date;

    public IncomeAndExpense() {
    }

    public IncomeAndExpense(long id, boolean isIncome, String type, long value, String name, Timestamp date) {
        this.id = id;
        this.isIncome=isIncome;
        this.type = type;
        this.value = value;
        this.name = name;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @Override
    public int compareTo(Object o) {
        Timestamp dateToCompare = ((IncomeAndExpense) o).getDate();
        Long d1 = this.date.getTime();
        Long d2 = dateToCompare.getTime();

        if (d2 > d1)
            return 1;
        else if (d1 > d2)
            return -1;
        else
            return 0;
    }
}
