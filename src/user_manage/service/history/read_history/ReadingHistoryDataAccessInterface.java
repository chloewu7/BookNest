package user_manage.service.history.read_history;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface ReadingHistoryDataAccessInterface {
    /**
     * Retrieves the reading history for a specific user.
     * @param userId The identifier of the user whose reading history is to be fetched.
     * @return A HashMap where each key is a timestamp and each value is the corresponding book title.
     */
    HashMap<LocalDateTime, String> getUserHistory(String userId);

    // Additional methods can be added here if needed, for example, methods for updating or deleting history records.
}
