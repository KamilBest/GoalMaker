package goal_maker.database.tables;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maarcin on 2018-01-14.
 */
@Entity
@Table(name = "constant_income", schema = "goal_maker")
public class ConstantIncome implements Serializable{
    private Long id_constant_income;
    private String name;
    private float value;
    private Long how_many_days;
    private Income income;

    public ConstantIncome(String name, float value, Long how_many_days, Income income) {
        this.name = name;
        this.value = value;
        this.how_many_days = how_many_days;
        this.income = income;
    }

    public ConstantIncome()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_constant_income", unique = true, nullable = false)
    public Long getId_constant_income() {
        return id_constant_income;
    }

    public void setId_constant_income(Long id_constant_income) {
        this.id_constant_income = id_constant_income;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Column(name = "how_many_days")
    public Long getHow_many_days() {
        return how_many_days;
    }

    public void setHow_many_days(Long how_many_days) {
        this.how_many_days = how_many_days;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "income")
    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }


}
