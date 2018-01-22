package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    private Date date;
    private String name;


    //    private Set<ConstantIncome> constantIncomes = new HashSet<ConstantIncome>(0);
//    private Set<ChangeableIncome> changeableIncomes = new HashSet<ChangeableIncome>(0);

    public Income() {

    }

    public Income(String type, long value) {
        this.type = type;
        this.value = value;
    }

    public Income(String type, long value, UserFinances user_finances) {
        this.type = type;
        this.value = value;
        this.user_finances = user_finances;
    }

    public Income(Long id_income, String type, long value, UserFinances user_finances) {
        this.id_income = id_income;
        this.type = type;
        this.value = value;
        this.user_finances = user_finances;
    }


    public Income(String type, long value, UserFinances user_finances, Date date, String name) {
        this.type = type;
        this.value = value;
        this.user_finances = user_finances;
        this.date = date;
        this.name = name;
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


    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "income")
//    public Set<ConstantIncome> getConstantIncomes() {
//        return constantIncomes;
//    }
//
//    public void setConstantIncomes(Set<ConstantIncome> constantIncomes) {
//        this.constantIncomes = constantIncomes;
//    }
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "income")
//    public Set<ChangeableIncome> getChangeableIncomes() {
//        return changeableIncomes;
//    }
//
//    public void setChangeableIncomes(Set<ChangeableIncome> changeableIncomes) {
//        this.changeableIncomes = changeableIncomes;
//    }
}
