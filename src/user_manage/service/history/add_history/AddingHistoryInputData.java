package user_manage.service.history.add_history;

import java.time.LocalDateTime;

public class AddingHistoryInputData{
    private String userId;
    private String bookId;
    private String actionType; // Consider using an enum for predefined actions
    private LocalDateTime timestamp;

    // Constructor
    public AddingHistoryInputData(String userId, String bookId, String actionType, LocalDateTime timestamp) {
        this.userId = userId;
        this.bookId = bookId;
        this.actionType = actionType;
        this.timestamp = timestamp;
    }

    // Getters
    public String getUserId() {
        return userId;
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
        this.userId = userId;
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