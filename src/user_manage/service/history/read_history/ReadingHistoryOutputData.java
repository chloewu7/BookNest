package user_manage.service.history.read_history;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ReadingHistoryOutputData {
    private final HashMap<LocalDateTime, String> history;

    public ReadingHistoryOutputData(HashMap<LocalDateTime, String> history) {
        this.history = history;
    }

    public HashMap<LocalDateTime, String> getHistory() {
        return history;
    }

    // Optionally, you can add more methods or fields here if needed in the future.
    // For example, methods for status messages or flags for operation success or failure.
}