package user_manage.service.history.read_history.interface_adpter;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class ReadingHistoryState {
    private LinkedHashMap<LocalDateTime, String> history;
    private String errorMessage;

    public ReadingHistoryState() {
        this.history = new LinkedHashMap<>();
        this.errorMessage = "";
    }

    public LinkedHashMap<LocalDateTime, String> getHistory() {
        return history;
    }

    public void setHistory(LinkedHashMap<LocalDateTime, String> history) {
        this.history = history;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
