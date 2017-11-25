package goal_maker.database.dao.user_dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import goal_maker.database.tables.user.GmUser;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public GmUser getUserByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//@SuppressWarnings("unchecked")
//	public GmUser getUserByLogin(String login)
//	{
//
////		List<User> users=new ArrayList<User>();
////		users = entityManager.createQuery("SELECT * FROM firm01.user_emes where login =?")
////			.setParameter(0, login)
////			.getResultList();
////		if (users.size() > 0) {
////			return users.get(0);
////		} else {
////			return null;
////		}
//		
//		//if (login.equals("test"))
//			return new User(1l, "Test", "Adam", "Kozłowski", "USER", "test", BCrypt.hashpw("test", BCrypt.gensalt()));
//		//else
//			//return null;
	//}
}
