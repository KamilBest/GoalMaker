package goal_maker.web.services.user_settings_service;

import goal_maker.database.dao.user_settings_dao.UserSettingsDao;
import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.UserFinances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserSettingsService")
public class UserSettingsServiceImpl implements UserSettingsService{
    @Autowired
    UserSettingsDao userSettingsDao;

    @Override
    public void editUserSettings(GmUser gmUser, String fieldToEdit, String newValue) {
        userSettingsDao.editUserSettings(gmUser,fieldToEdit,newValue);

    }

    @Override
    public void editUserFinances(UserFinances userFinances, String fieldToEdit, long newValue) {
        userSettingsDao.editUserFinances(userFinances,fieldToEdit,newValue);
    }
}
