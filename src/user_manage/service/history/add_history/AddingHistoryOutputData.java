package user_manage.service.history.add_history;

public class AddingHistoryOutputData {
    private boolean success;
    private String message;


    // Constructor
    public AddingHistoryOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    // Setters - if you want to allow modification after object creation
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}