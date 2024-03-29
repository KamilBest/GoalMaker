package goal_maker.web.services.user_service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goal_maker.database.dao.user_dao.UserDao;
import goal_maker.database.tables.GmUser;


@Service("userDetailsService")
public class UserLoginDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    GmUser gmUser = null;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        gmUser = userDao.getUserByLogin(login);
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        if (gmUser == null) {
            throw new UsernameNotFoundException("No user found with login: " + login);
        }

        User user = new User(gmUser.getLogin(), gmUser.getPassword(), true, true, true, gmUser.getIsActive(),
                Arrays.asList(authority));
        return user;

    }
}

	

