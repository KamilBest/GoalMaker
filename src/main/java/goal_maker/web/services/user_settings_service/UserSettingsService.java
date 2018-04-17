package goal_maker.web.services.user_settings_service;

import goal_maker.database.tables.GmUser;
import goal_maker.database.tables.UserFinances;

public interface UserSettingsService {
    void editUserSettings(GmUser gmUser, String fieldToEdit, String newValue);
    void editUserFinances(UserFinances userFinances,String fieldToEdit,long newValue);
}
