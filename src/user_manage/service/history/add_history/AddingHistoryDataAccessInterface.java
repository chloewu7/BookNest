package user_manage.service.history.add_history;

import user_manage.entity.User;

public interface AddingHistoryDataAccessInterface {
    User getUserById(String userId);

    void addHistoryToUser(User user, String historyRecord);
}
