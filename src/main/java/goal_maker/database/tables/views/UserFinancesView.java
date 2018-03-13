package goal_maker.database.tables.views;


import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
/*
@Table(name = "user_finances_view")
*/
@Immutable

public class UserFinancesView {
    private Integer idUserFinances;
    private Long realAccountBalance;
    private Long accountBalance;
    private Long goalBalance;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user_finances", unique = true, nullable = false,insertable = false, updatable = false)
    public Integer getIdUserFinances() {
        return idUserFinances;
    }

    public void setIdUserFinances(Integer idUserFinances) {
        this.idUserFinances = idUserFinances;
    }

    @Column(name = "real_account_balance")
    public Long getRealAccountBalance() {
        return realAccountBalance;
    }

    public void setRealAccountBalance(Long realAccountBalance) {
        this.realAccountBalance = realAccountBalance;
    }

    @Column(name = "accout_balance")
    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Column(name = "goal_balance")
    public Long getGoalBalance() {
        return goalBalance;
    }

    public void setGoalBalance(Long goalBalance) {
        this.goalBalance = goalBalance;
    }
}
