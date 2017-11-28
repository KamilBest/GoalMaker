package goal_maker.database.dao.user_dao;

import goal_maker.database.tables.user.GmUser;

public interface UserDao {
	public GmUser getUserByLogin(String login);
	
}
