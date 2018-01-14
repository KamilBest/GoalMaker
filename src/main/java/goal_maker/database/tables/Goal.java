package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goal", schema = "goal_maker")

public class Goal implements Serializable {
    private long id_goal;
    private String name;
    private Category category;
    private long value;
    private long number_of_days;
    private String picture_name;

    public Goal() {
    }

    public Goal(String name, Category category, long value) {
        this.name = name;
        this.category = category;
        this.value = value;
    }

    public Goal(String name, Category category, long value, long number_of_days, String picture_name) {
        this.name = name;
        this.category = category;
        this.value = value;
        this.number_of_days = number_of_days;
        this.picture_name = picture_name;
    }

    public Goal(long id_goal, String name, Category category, long value, long number_of_days, String picture_name) {
        this.id_goal = id_goal;
        this.name = name;
        this.category = category;
        this.value = value;
        this.number_of_days = number_of_days;
        this.picture_name = picture_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    @Column(name="picture_name")
    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }
}