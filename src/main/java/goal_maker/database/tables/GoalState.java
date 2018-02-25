package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goal_state", schema = "goal_maker")
public class GoalState implements Serializable {
    private long id_goal_state;
    private String name;

    public GoalState() {
    }

    public GoalState(long id_goal_state, String name) {
        this.id_goal_state = id_goal_state;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_goal_state", unique = true, nullable = false)
    public long getId_goal_state() {
        return id_goal_state;
    }

    public void setId_goal_state(long id_goal_state) {
        this.id_goal_state = id_goal_state;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
