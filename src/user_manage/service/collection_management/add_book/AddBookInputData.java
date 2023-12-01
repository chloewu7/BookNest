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
    String getUserName(){
        return userName;
    }

    String getListName() {
        return listName;
    }

    Book getBook() {
        return book;
    }
}
