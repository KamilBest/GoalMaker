package goal_maker.database.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "goal", schema = "goal_maker")

public class Goal implements Serializable {
    private Long id_goal;
    private String name;
    private String category;
    private long value;
    private long number_of_days;
    private String picture;

    public Goal() {
    }

    public Goal(Long id_goal, String name, String category, long value, long number_of_days, String picture) {
        this.id_goal = id_goal;
        this.name = name;
        this.category = category;
        this.value = value;
        this.number_of_days = number_of_days;
        this.picture = picture;
    }

    @Id
    @Column(name = "id_goal", unique = true, nullable = false)
    public long getId_goal() {
        return id_goal;
    }

    public void setId_goal(long id_goal) {
        this.id_goal = id_goal;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "value")
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Column(name = "number_of_days")
    public long getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(long number_of_days) {
        this.number_of_days = number_of_days;
    }

    @Column(name="picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
