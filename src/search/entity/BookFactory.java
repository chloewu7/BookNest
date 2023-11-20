package search.entity;

public interface BookFactory {
    Book createBook(String title, String author, String category, String ISBN);
}
