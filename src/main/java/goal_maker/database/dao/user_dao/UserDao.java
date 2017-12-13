package goal_maker.database.dao.user_dao;

import goal_maker.database.tables.GmUser;

import java.util.List;

public interface UserDao {

    public GmUser getUserByLogin(String login);

    public void addUser(GmUser user);

    public List<GmUser> getUsersList();


}
