package goal_maker.database.tables;

import javax.persistence.*;

@Entity
@Table(name = "permission", schema = "goal_maker")
public class UserPermission implements java.io.Serializable {
    private int id;
    private String name;

    public UserPermission() {
    }

    public UserPermission(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_permission", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
