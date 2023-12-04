package user_manage.service.collection_management.show_books_in_list;

public class ShowBooksInListInputData {

    final private String userName;
    final private String listName;

    public ShowBooksInListInputData(String userName, String listName) {
        this.userName = userName;
        this.listName = listName;
    }
    public String getUserName() {return userName; }
    public String getListName() {
        return listName;
    }
}
