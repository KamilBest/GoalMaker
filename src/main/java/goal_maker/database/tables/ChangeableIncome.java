package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maarcin on 2018-01-14.
 */
@Entity
@Table(name = "changeable_income", schema = "goal_maker")
public class ChangeableIncome implements Serializable {
    private Long id_changable_income;
    private String name;
    private float value;
    private Income income;

    public ChangeableIncome(String name, float value, Income income) {
        this.name = name;
        this.value = value;
        this.income = income;
    }

    public ChangeableIncome() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_changeable_income", unique = true, nullable = false)
    public Long getId_changable_income() {
        return id_changable_income;
    }

    public void setId_changable_income(Long id_changable_income) {
        this.id_changable_income = id_changable_income;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_income")
    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }
}
