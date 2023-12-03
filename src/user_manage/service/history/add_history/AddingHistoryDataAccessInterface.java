package user_manage.service.history.add_history;

import user_manage.entity.History;
import user_manage.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public interface AddingHistoryDataAccessInterface {
    User getUserByName(String userName);


    void addHistoryToUser(User user, History history);

    ArrayList<String> getHistoryByUserId(String userId);
}
