package user_manage.service.history.read_history;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ReadingHistoryOutputData {
    private final Map<LocalDateTime, String> history;

    public ReadingHistoryOutputData(Map<LocalDateTime, String> history) {
        this.history = history;
    }

    public Map<LocalDateTime, String> getHistory() {
        return history;
    }

    // Optionally, you can add more methods or fields here if needed in the future.
    // For example, methods for status messages or flags for operation success or failure.
}