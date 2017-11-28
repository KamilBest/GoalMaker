package goal_maker.database.dao.user_dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import goal_maker.database.tables.user.GmUser;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Get user by login
	 */
	@Override
	public GmUser getUserByLogin(String login) {
		String sqlSelect = "SELECT id_user, login, password, is_active FROM goal_maker.user_gm WHERE login = '"
				+ login + "'";
		Query query = entityManager.createNativeQuery(sqlSelect, GmUser.class);
		GmUser user = (GmUser) query.getSingleResult();
		return user;
	}

	
}
