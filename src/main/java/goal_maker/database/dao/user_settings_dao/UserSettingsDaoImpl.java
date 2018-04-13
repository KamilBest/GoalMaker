package goal_maker.database.dao.user_settings_dao;


import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.UserFinances;
import goal_maker.web.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserSettingsDaoImpl implements UserSettingsDao {


    @PersistenceContext
    private EntityManager entityManager;

    /**
     * HASLO I MAJLA ZROBIC NA JUTRO
     * @param gmUser
     * @param fieldToEdit
     * @param newValue
     */
    @Transactional
    @Override
    public void editUserSettings(GmUser gmUser, String fieldToEdit, String newValue) {

        String sqlUpdate = "UPDATE goal_maker.user_gm  SET"+" "+fieldToEdit +"='" + newValue + "'  WHERE login='" + gmUser.getLogin() + "'";
        Query query = entityManager.createNativeQuery(sqlUpdate, GmUser.class);

        query.executeUpdate();
    }
    @Transactional
    @Override
    public void editUserFinances(UserFinances userFinances, String fieldToEdit, long newValue) {

        String sqlUpdate = "UPDATE goal_maker.user_finances  SET "+ " "+ fieldToEdit +"=" + newValue + "  WHERE id_user_finances="+ userFinances.getId_user_finances();
        Query query = entityManager.createNativeQuery(sqlUpdate, UserFinances.class);

        query.executeUpdate();

    }
}

