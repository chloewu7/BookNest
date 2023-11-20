package search.entity;

import search.entity.Book;
import search.entity.BookFactory;
import search.entity.CommonBook;

public class CommonBookFactory implements BookFactory {
    public Book createBook(String title, String author, String category, String ISBN){
        return new CommonBook(title, author, category, ISBN);
    }
}
