package user_manage.service.history.read_history;

//import user_manage.service.history.add_history.AddingHistoryInputData;



public class ReadingHistoryInputData {
    private String bookName;
    private String userName;

    // Constructor
    public ReadingHistoryInputData(String userName, String bookName) {
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


}

