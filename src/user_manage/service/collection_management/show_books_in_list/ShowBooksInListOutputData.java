package user_manage.service.collection_management.show_books_in_list;

import search.entity.Book;

import java.util.List;
import java.util.Map;

public class ShowBooksInListOutputData {

    private final Map<String, String> books;

    public ShowBooksInListOutputData(Map<String, String> books) {
        this.books = books;
    }

    public Map<String, String> getBooks() {
        return books;
    }
}
