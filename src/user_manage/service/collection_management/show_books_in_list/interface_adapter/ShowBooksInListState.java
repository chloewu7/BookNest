package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import user_manage.entity.CollectionList;
import user_manage.entity.CommonCollectionList;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowBooksInListState {
    private String userName = "";
    private String listName = "";
    private Map<String, String> books = new HashMap<>();

    public ShowBooksInListState(ShowBooksInListState copy){
        userName = copy.userName;
        listName = copy.listName;
        books = copy.books;
    }
    public ShowBooksInListState(){}
    public String getUserName() {
        return userName;
    }

    public String getListName() {
        return listName;
    }

    public Map<String, String> getBooks() {
        return books;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setBooks(Map<String, String> books) {
        this.books = books;
    }
}
