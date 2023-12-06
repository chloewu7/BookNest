package user_manage.service.history.read_history.interface_adpter;

import java.util.ArrayList;
import java.util.List;

public class ReadingHistoryState {
    private List<String> history = new ArrayList<>();
    private String errorMessage = null;

    private String userName = "";

    private String bookName = "";

    public ReadingHistoryState(ReadingHistoryState copy) {
        history = copy.history;
        errorMessage = copy.errorMessage;
        userName = copy.userName;
        bookName = copy.bookName;}
    public ReadingHistoryState(){};

    public List<String> getHistory() {
        return this.history;
    }

    public void setHistory(List<String> history) {
        history.add(this.bookName);
        this.history = history;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setUserName(String userName){this.userName = userName;}

    public String getUserName(){return this.userName;}



    public void setReadBook(String readBookTitle) {this.bookName =readBookTitle;
        this.history.add(readBookTitle);
    }

    public String getReadBook(){return this.bookName;}
}

