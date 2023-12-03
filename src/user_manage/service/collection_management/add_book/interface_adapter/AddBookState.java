package user_manage.service.collection_management.add_book.interface_adapter;

import user_manage.entity.CollectionList;
import user_manage.entity.CommonCollectionList;

import java.util.List;

public class AddBookState {
    private String userName = "";
    private String listName = "";
    private String bookName = "";
    private String bookAuthor = "";
    private String addBookSuccess = null;
    private String addBookError = null;

    public AddBookState(AddBookState copy){
        userName = copy.userName;
        listName = copy.listName;
        bookName = copy.bookName;
        bookAuthor = copy.bookAuthor;
        addBookSuccess = copy.addBookSuccess;
        addBookError = copy.addBookError;
    }
    public AddBookState(){}
    public String getUserName() {
        return userName;
    }
    public String getListName() {
        return listName;
    }
    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getAddBookSuccess() {
        return addBookSuccess;
    }

    public String getAddBookError() {
        return addBookError;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setAddBookSuccess(String addBookSuccess) {
        this.addBookSuccess = addBookSuccess;
    }

    public void setAddBookError(String addBookError) {
        this.addBookError = addBookError;
    }
}
