package goal_maker.database.dao.user_settings_dao;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.UserFinances;


public interface UserSettingsDao {
    void editUserSettings(GmUser gmUser, String fieldToEdit, String newValue);
    void editUserFinances(UserFinances userFinances, String fieldToEdit, long newValue);


}
