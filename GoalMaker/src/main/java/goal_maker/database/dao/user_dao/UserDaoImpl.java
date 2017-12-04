package goal_maker.database.dao.user_dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import goal_maker.config.Security.Enrycption;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import goal_maker.database.tables.user.GmUser;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceContext
    private Enrycption encryption;

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

    /**
     * Add user method with user param
     */
    @Transactional
    @Override
    public void addUser(GmUser user) {

        user.setPassword(encryption.encryptPassword(user.getPassword()));
        String sqlInsert = "INSERT INTO goal_maker.user_gm(\n" +
                "            id_user, login, password, name, surname, email, date_of_birth, \n" +
                "            is_active)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, ?, \n" +
                "            ?);";
        Query query = entityManager.createNativeQuery(sqlInsert, GmUser.class);
        query.setParameter(1, user.getId());
        query.setParameter(2, user.getLogin());
        query.setParameter(3, user.getPassword());
        query.setParameter(4, user.getName());
        query.setParameter(5, user.getSurname());
        query.setParameter(6, user.getEmail());
        query.setParameter(7, user.getDateOfBirth());
        query.setParameter(8, user.isActive());
        query.executeUpdate();

    }

    @Override
    public List<GmUser> getUsersList() {
        String sqlSelect = "SELECT id_user, login, password, is_active FROM goal_maker.user_gm";
        List<GmUser> userList = entityManager.createNativeQuery(sqlSelect, GmUser.class).getResultList();
        return userList;
    }


}
