package user_manage.service.collection_management.add_book;

import search.entity.Book;

public class AddBookInputData {
    final private String userName;
    final private String listName;

    final private Book book;

    public AddBookInputData(String userName, String listName, Book book) {
        this.userName = userName;
        this.listName = listName;
        this.book = book;
    }
    public String getUserName(){
        return userName;
    }

    public String getListName() {
        return listName;
    }

    public Book getBook() {
        return book;
    }
}
