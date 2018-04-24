package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "periodical_income", schema = "goal_maker")

public class PeriodicalIncome implements Serializable {

    private long idPerdiodicalIncome;
    private String name;
    private long value;
    private Date initDate;
    private  long periodLength;
    private Date lastIncomeDate;
    private UserFinances userFinances;

    public PeriodicalIncome() {
    }

    public PeriodicalIncome(long idPerdiodicalIncome, String name, long value, Date initDate, long periodLength, Date lastIncomeDate, UserFinances userFinances) {
        this.idPerdiodicalIncome = idPerdiodicalIncome;
        this.name = name;
        this.value=value;
        this.initDate = initDate;
        this.periodLength = periodLength;
        this.lastIncomeDate = lastIncomeDate;
        this.userFinances = userFinances;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_periodical_income", unique = true, nullable = false)
    public long getIdPerdiodicalIncome() {
        return idPerdiodicalIncome;
    }

    public void setIdPerdiodicalIncome(long idPerdiodicalIncome) {
        this.idPerdiodicalIncome = idPerdiodicalIncome;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value")
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Column(name = "init_date")
    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    @Column(name = "period_length")
    public long getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(long periodLength) {
        this.periodLength = periodLength;
    }

    @Column(name = "last_income_date")
    public Date getLastIncomeDate() {
        return lastIncomeDate;
    }

    public void setLastIncomeDate(Date lastIncomeDate) {
        this.lastIncomeDate = lastIncomeDate;
    }

    @ManyToOne
    @JoinColumn(name = "id_user_finances")
    public UserFinances getUserFinances() {
        return userFinances;
    }

    public void setUserFinances(UserFinances userFinances) {
        this.userFinances = userFinances;
    }
}
