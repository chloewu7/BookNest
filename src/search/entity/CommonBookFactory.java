package search.entity;

public class CommonBookFactory implements BookFactory {
    public Book createBook(String title, String author, String category, String ISBN){
        return new CommonBook(title, author, category, ISBN);
    }
}
