package goal_maker.web.services.user_service;

import goal_maker.database.tables.user.GmUser;

import java.util.List;

public interface UserService {
	public GmUser getUserByLogin(String login);
	public List<GmUser> getUsersList();
	public void addUser(GmUser user);
}
