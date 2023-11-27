package search.service.interface_adapter;
import search.entity.Book;
import java.util.List;

public class SearchState {
    private String books = "";
    private String notFound = null;
    private String keywordError = null;
    public SearchState(SearchState copy){
        books = copy.books;
        notFound = copy.notFound;
        keywordError = copy.keywordError;
    }
    public SearchState(){}
    public String getBooks(){return books;}
    public String getNotFound(){return notFound;}
    public String getKeywordError(){return keywordError;}
    public void setBooks(String books){this.books = books;}
    public void setNotFound(String notFound){this.notFound = notFound;}
    public void setKeywordError(String keywordError){this.keywordError = keywordError;}


}
