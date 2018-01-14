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
    private Long account_balance;
    private Long current_state_to_goal;
    private Set<Income> incomes = new HashSet<Income>(0);

    public UserFinances(Long account_balance, Long current_state_to_goal, Set<Income> incomes) {
        this.account_balance = account_balance;
        this.current_state_to_goal = current_state_to_goal;
        this.incomes = incomes;
    }

    public UserFinances() {

    }

    public UserFinances(Long account_balance, Long current_state_to_goal) {
        this.account_balance = account_balance;
        this.current_state_to_goal = current_state_to_goal;
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

    @Column(name = "account_balance")
    public Long getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(Long account_balance) {
        this.account_balance = account_balance;
    }

    @Column(name = "current_state_to_goal")
    public Long getCurrent_state_to_goal() {
        return current_state_to_goal;
    }

    public void setCurrent_state_to_goal(Long current_state_to_goal) {
        this.current_state_to_goal = current_state_to_goal;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_finances")
    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }
}
