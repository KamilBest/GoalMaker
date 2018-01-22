package goal_maker.database.tables;


import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "expenses", schema = "goal_maker")
public class Expenses implements Serializable{

    private long idExpenses;
    private String type;
    private long value;
    private String name;
    private UserFinances userFinances;
    private Timestamp date;

    public Expenses() {

    }

    public Expenses(long idExpenses, String type, long value, String name, UserFinances userFinances, Timestamp date) {

        this.idExpenses = idExpenses;
        this.type = type;
        this.value = value;
        this.name = name;
        this.userFinances = userFinances;
        this.date = date;
    }

    public Expenses(String type, long value, String name, UserFinances userFinances, Timestamp date) {
        this.type = type;
        this.value = value;
        this.name = name;
        this.userFinances = userFinances;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_expenses", unique = true, nullable = false)
    public long getIdExpenses() {
        return idExpenses;
    }

    public void setIdExpenses(long idExpenses) {
        this.idExpenses = idExpenses;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne
    @JoinColumn(name = "id_user_finances")
    public UserFinances getUserFinances() {
        return userFinances;
    }

    public void setUserFinances(UserFinances userFinances) {
        this.userFinances = userFinances;
    }

    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
