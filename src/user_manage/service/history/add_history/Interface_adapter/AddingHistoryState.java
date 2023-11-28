package user_manage.service.history.add_history.Interface_adapter;

public class AddingHistoryState {
    private String successMessage = "";
    private String errorMessage = null;

    // Copy constructor
    public AddingHistoryState(AddingHistoryState copy) {
        this.successMessage = copy.successMessage;
        this.errorMessage = copy.errorMessage;
    }

    // Default constructor
    public AddingHistoryState() {
    }

    // Getters
    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Setters
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "AddingHistoryState{" +
                "successMessage='" + successMessage + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}