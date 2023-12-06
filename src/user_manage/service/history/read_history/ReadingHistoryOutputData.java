package user_manage.service.history.read_history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingHistoryOutputData {
    private final List<String> history;

    public ReadingHistoryOutputData(List<String> history) {
        this.history = history;
    }

    public List<String> getHistory() {
        return this.history;
    }

    // Optionally, you can add more methods or fields here if needed in the future.
    // For example, methods for status messages or flags for operation success or failure.
}