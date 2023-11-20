package search.entity;

import search.entity.Book;

public interface BookFactory {
    Book createBook(String title, String author, String category, String ISBN);
}
