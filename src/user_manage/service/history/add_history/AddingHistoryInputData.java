package user_manage.service.history.add_history;

import java.time.LocalDateTime;

public class AddingHistoryInputData{
    private String userName;
    private String bookId;
    private String actionType; // Consider using an enum for predefined actions
    private LocalDateTime timestamp;

    // Constructor
    public AddingHistoryInputData(String userName, String bookId, String actionType, LocalDateTime timestamp) {
        this.userName = userName;
        this.bookId = bookId;
        this.actionType = actionType;
        this.timestamp = timestamp;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getBookId() {
        return bookId;
    }

    public String getActionType() {
        return actionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setUserId(String userId) {
        this.userName = userName;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}