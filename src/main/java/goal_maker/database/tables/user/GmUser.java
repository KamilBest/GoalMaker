package goal_maker.database.tables.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public GmUser() {
    }

    public GmUser(long id, String login,
                  String password, boolean isActive) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Id
    @Column(name = "id_user", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long i) {
        this.id = i;
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


}
