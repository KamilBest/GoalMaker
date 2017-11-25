package goal_maker.database.tables.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", catalog="public")
public class GmUser {

	private Long id;
	private String login;
	private String password;
	private boolean isActive;
	
	public GmUser() {}

	public GmUser(Long id, String login,
			String password, boolean isActive) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	@Id
	@Column(name = "id_user",unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}
	
	@Column(name = "login")
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
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
