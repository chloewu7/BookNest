package user_manage.service.collection_management.show_books_in_list;

import java.util.Map;

public class ShowBooksInListOutputData {
    private String listName;

    private Map<String, String> books;

    public ShowBooksInListOutputData(String listName, Map<String, String> books) {
        this.listName = listName;
        this.books = books;
    }

    public String getListName() {
        return listName;
    }

    public Map<String, String> getBooks() {
        return books;
    }
}
