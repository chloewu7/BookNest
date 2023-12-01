package user_manage.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonCollectionList implements CollectionList{
    private final String userName;
    private final String listName;
    private final Map<String, String> books;

    public CommonCollectionList(String userName, String listName, Map<String, String> books) {
        this.userName = userName;
        this.listName = listName;
        this.books = books;
    }

    public String getUserName() {
        return userName;
    }

    public String getListName() {
        return listName;
    }

    public Map<String, String> getBooks() {return books;}

    public List<String> getBooksName() {
        return new ArrayList<>(books.keySet());
    }

}
