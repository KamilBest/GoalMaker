package goal_maker.database.dao.user_dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import goal_maker.config.security.Encryption;
import org.springframework.stereotype.Repository;

import goal_maker.database.tables.GmUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get user by login
     */
    @Override
    public GmUser getUserByLogin(String login) {
        String sqlSelect = "SELECT id_user, login, password, name, surname, email, date_of_birth, is_active, id_goal " +
                "FROM goal_maker.user_gm WHERE login = '"
                + login + "'";
        Query query = entityManager.createNativeQuery(sqlSelect, GmUser.class);
        GmUser user = (GmUser) query.getSingleResult();
        return user;
    }

    /**
     * Add user method with user param
     */
    @Transactional
    @Override
    public void addUser(GmUser user) {

        user.setPassword(Encryption.encryptPassword(user.getPassword()));
        String sqlInsert = "INSERT INTO goal_maker.user_gm(login, password, name, surname, email, date_of_birth, " +
                "is_active) VALUES (?, ?, ?, ?, ?, ?, ?);";
        Query query = entityManager.createNativeQuery(sqlInsert, GmUser.class);
        query.setParameter(1, user.getLogin());
        query.setParameter(2, user.getPassword());
        query.setParameter(3, user.getName());
        query.setParameter(4, user.getSurname());
        query.setParameter(5, user.getEmail());
        query.setParameter(6, user.getDateOfBirth());
        query.setParameter(7, user.getIsActive());

        query.executeUpdate();

    }

    @Override
    public List<GmUser> getUsersList() {
        String sqlSelect = "SELECT id_user, login, password, name, surname, email, date_of_birth, is_active, id_goal FROM goal_maker.user_gm";
        List<GmUser> userList = entityManager.createNativeQuery(sqlSelect, GmUser.class).getResultList();
        return userList;
    }


}
