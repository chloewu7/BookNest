package search.service;
import search.entity.Book;
import java.util.List;

public interface SearchDataAccessInterface {
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByCategory(String category);
    List<Book> searchByISBN(String ISBN);
}
