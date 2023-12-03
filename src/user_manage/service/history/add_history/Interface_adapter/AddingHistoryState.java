package user_manage.service.history.add_history.Interface_adapter;

import java.util.ArrayList;

public class AddingHistoryState {
    private String successMessage = "";
    private String errorMessage = null;

    private ArrayList<String> history;

    // Copy constructor
    public AddingHistoryState(AddingHistoryState copy) {
        this.successMessage = copy.successMessage;
        this.errorMessage = copy.errorMessage;
        this.history = copy.history;
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

    public void setReadBook(String readBookName){this.history.add(readBookName);}


    @Override
    public String toString() {
        return "AddingHistoryState{" +
                "successMessage='" + successMessage + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}