package user_manage.service.history.add_history;

import user_manage.entity.History;
import user_manage.entity.User;

import java.util.List;

public interface AddingHistoryDataAccessInterface {


    void addHistoryToUser(User user, String bookName);

    List<String> getHistoryByUserId(String userId);

    User getUserByName(String userName);
}
