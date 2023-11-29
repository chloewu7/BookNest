package user_manage.service.history.read_history;

import java.time.LocalDateTime;

import java.util.Map;

public interface ReadingHistoryDataAccessInterface {
    /**
     * Retrieves the reading history for a specific user.
     * @param userId The identifier of the user whose reading history is to be fetched.
     * @return A HashMap where each key is a timestamp and each value is the corresponding book title.
     */
    Map<LocalDateTime, String> getHistoryByUserId(String userId);


}
