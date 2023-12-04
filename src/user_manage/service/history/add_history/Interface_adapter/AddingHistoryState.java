package user_manage.service.history.add_history.Interface_adapter;

import user_manage.service.history.add_history.AddingHistoryInputData;
import user_manage.service.history.read_history.ReadingHistoryInputData;

import java.util.ArrayList;

public class AddingHistoryState {
    private String successMessage = "";
    private String errorMessage = null;

    private String userName;

    private String bookName;

    // Copy constructor
    public AddingHistoryState(AddingHistoryState copy) {
        this.successMessage = copy.successMessage;
        this.errorMessage = copy.errorMessage;;
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

    public void setReadBook(String readBookName){this.bookName =bookName;}

    public void setUserName(String userName){this.userName = userName;}

    public AddingHistoryInputData setReadBookInput(String username, String booName){
        AddingHistoryInputData addingHistoryInputData = new AddingHistoryInputData(userName, bookName);
        return addingHistoryInputData;
    }


    @Override
    public String toString() {
        return "AddingHistoryState{" +
                "successMessage='" + successMessage + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}