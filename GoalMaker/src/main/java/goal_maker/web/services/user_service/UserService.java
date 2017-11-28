package goal_maker.web.services.user_service;

import goal_maker.database.tables.user.GmUser;

public interface UserService {
	public GmUser getUserByLogin(String login);
}
