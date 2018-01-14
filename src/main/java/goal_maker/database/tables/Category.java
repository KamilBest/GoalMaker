package goal_maker.database.tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category", schema = "goal_maker")
public class Category implements Serializable {
    private Long id_category;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_category", unique = true, nullable = false)
    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
