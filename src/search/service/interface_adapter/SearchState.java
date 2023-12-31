package search.service.interface_adapter;
import search.entity.Book;
import java.util.List;

public class SearchState {
    private List<Book> books;
    private String notFound = null;
    private String keywordError = null;
    public Book collectedBook;

    public String commentBookTitle;

    public String userName = "";

    public String readBookTitle;

    public SearchState(SearchState copy){
        books = copy.books;
        notFound = copy.notFound;
        keywordError = copy.keywordError;
    }
    public SearchState(){}
    public List<Book> getBooks(){return books;}
    public String getNotFound(){return notFound;}
    public String getKeywordError(){return keywordError;}
    public String getCommentBookTitle(){return commentBookTitle;}
    public Book getCollectedBook(){return collectedBook;}
    public String getUserName(){return userName;}
    public String getReadBookTitle(){return readBookTitle;}

    public void setCommentBookTitle(String title){this.commentBookTitle = title;}
    public void setCollectedBook(Book collectedBook){this.collectedBook = collectedBook;}
    public void setBooks(List<Book> books){this.books = books;}
    public void setNotFound(String notFound){this.notFound = notFound;}
    public void setKeywordError(String keywordError){this.keywordError = keywordError;}
    public void setUserName(String userName){this.userName = userName;}
    public void setReadBookTitle(String readBookTitle){this.readBookTitle = readBookTitle;}


}
