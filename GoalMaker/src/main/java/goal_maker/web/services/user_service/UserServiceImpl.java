package goal_maker.web.services.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal_maker.database.dao.user_dao.UserDao;
import goal_maker.database.tables.user.GmUser;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public GmUser getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

}
