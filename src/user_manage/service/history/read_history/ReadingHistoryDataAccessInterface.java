package user_manage.service.history.read_history;

import user_manage.entity.User;

import java.util.List;

public interface ReadingHistoryDataAccessInterface {
    /**
     * Retrieves the reading history for a specific user.
     *
     * @param userId The identifier of the user whose reading history is to be fetched.
     * @return A HashMap where each key is a timestamp and each value is the corresponding book title.
     */
    List<String> getHistoryByUserId(String userId);

    void addHistoryToUser(String username, String bookName);


    boolean user_exist(String username);
}



