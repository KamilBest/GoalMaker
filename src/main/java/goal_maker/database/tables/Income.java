package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maarcin on 2018-01-14.
 */
@Entity
@Table(name = "income", schema = "goal_maker")
public class Income implements Serializable {
    private Long id_income;
    private String type;
    private long value;
    private UserFinances user_finances;
    private Set<ConstantIncome> constantIncomes = new HashSet<ConstantIncome>(0);
    private Set<ChangeableIncome> changeableIncomes = new HashSet<ChangeableIncome>(0);

    public Income() {

    }

    public Income(Long id_income, String type, long value, UserFinances user_finances, Set<ConstantIncome> constantIncomes, Set<ChangeableIncome> changeableIncomes) {
        this.id_income = id_income;
        this.type = type;
        this.value = value;
        this.user_finances = user_finances;
        this.constantIncomes = constantIncomes;
        this.changeableIncomes = changeableIncomes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_income", unique = true, nullable = false)
    public Long getId_income() {
        return id_income;
    }

    public void setId_income(Long id_income) {
        this.id_income = id_income;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "value")
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "id_user_finances")
    public UserFinances getUser_finances() {
        return user_finances;
    }

    public void setUser_finances(UserFinances user_finances) {
        this.user_finances = user_finances;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "income")
    public Set<ConstantIncome> getConstantIncomes() {
        return constantIncomes;
    }

    public void setConstantIncomes(Set<ConstantIncome> constantIncomes) {
        this.constantIncomes = constantIncomes;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "income")
    public Set<ChangeableIncome> getChangeableIncomes() {
        return changeableIncomes;
    }

    public void setChangeableIncomes(Set<ChangeableIncome> changeableIncomes) {
        this.changeableIncomes = changeableIncomes;
    }
}
