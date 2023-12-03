package user_manage.service.history.add_history;

import java.time.LocalDateTime;

public class AddingHistoryInputData{
    private String bookName;
    private String userName;

    // Constructor
    public AddingHistoryInputData(String userName, String bookName) {
        this.userName = userName;
        this.bookName = bookName;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getBookName() {
        return bookName;
    }


    // Setters
    public void setUserId(String userId) {
        this.userName = userName;
    }

    public void setBookName(String bookId) {
        this.bookName = bookName;
    }

}