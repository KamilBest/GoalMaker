package goal_maker.database.tables;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_gm", schema = "goal_maker")
public class GmUser implements java.io.Serializable {

    private long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;
    private boolean isActive;
    private List<Goal> goals;
    private UserFinances userFinances;
    private UserPermission permission;

    public GmUser() {
    }

    public GmUser(String login, String password, String name, String surname, String email, Date dateOfBirth, boolean isActive, List<Goal> goals) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.goals = goals;
    }

    public GmUser(long id, String login,
                  String password, boolean isActive) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public GmUser(long id, String login, String password, String name, String surname, String email, Date dateOfBirth, boolean isActive) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
    }

    public GmUser(long id, String login, String password, String name, String surname, String email, Date dateOfBirth, boolean isActive, List<Goal> goals, UserFinances userFinances, UserPermission permission) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.goals = goals;
        this.userFinances = userFinances;
        this.permission = permission;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long i) {
        this.id = i;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "login", unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_active")
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_goal")
    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_finances")
    public UserFinances getUserFinances() {
        return userFinances;
    }

    public void setUserFinances(UserFinances userFinances) {
        this.userFinances = userFinances;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permission")
    public UserPermission getPermission() {
        return permission;
    }

    public void setPermission(UserPermission permission) {
        this.permission = permission;
    }


    //PRINT OBJECTS
    public String printUserGoals()
    {
        if(goals.isEmpty() || goals==null)
            return null;
        return goals.toString();
    }
}

