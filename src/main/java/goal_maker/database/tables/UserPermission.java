package goal_maker.database.tables;

import javax.persistence.*;

@Entity
@Table(name = "permission", schema = "goal_maker")
public class UserPermission implements java.io.Serializable {
    private long id;
    private String name;

    public UserPermission() {
    }

    public UserPermission(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
