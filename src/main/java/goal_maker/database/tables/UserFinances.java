package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maarcin on 2018-01-14.
 */
@Entity
@Table(name = "user_finances", schema = "goal_maker")
public class UserFinances implements Serializable {
    private Long id_user_finances;
    /*
        private Long account_balance;
    */
    private Long goal_balance;
    private Set<Income> incomes = new HashSet<Income>(0);
    private Long real_account_balance;

    public UserFinances() {

    }

    public UserFinances(Long id_user_finances, Long goal_balance, Long real_account_balance) {
        this.id_user_finances = id_user_finances;
        this.goal_balance = goal_balance;
        this.real_account_balance = real_account_balance;
    }


    public UserFinances(Long real_account_balance, Long goal_balance) {
        this.real_account_balance = real_account_balance;
        this.goal_balance = goal_balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user_finances", unique = true, nullable = false)
    public Long getId_user_finances() {
        return id_user_finances;
    }

    public void setId_user_finances(Long id_user_finances) {
        this.id_user_finances = id_user_finances;
    }

 /*   @Column(name = "account_balance")
    public Long getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(Long account_balance) {
        this.account_balance = account_balance;
    }*/

    @Column(name = "goal_balance")
    public Long getGoal_balance() {
        return goal_balance;
    }

    public void setGoal_balance(Long goal_balance) {
        this.goal_balance = goal_balance;
    }

    @Column(name = "real_account_balance")
    public Long getReal_account_balance() {
        return real_account_balance;
    }

    public void setReal_account_balance(Long real_account_balance) {
        this.real_account_balance = real_account_balance;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user_finances")
    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }


}
