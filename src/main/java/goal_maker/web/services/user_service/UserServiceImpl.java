package goal_maker.web.services.user_service;

import goal_maker.database.dao.goal_dao.GoalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal_maker.database.dao.user_dao.UserDao;
import goal_maker.database.tables.GmUser;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public GmUser getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public List<GmUser> getUsersList() {
        return userDao.getUsersList();
    }

    //add user
    @Override
    public void addUser(GmUser user) {
        userDao.addUser(user);
    }



}
