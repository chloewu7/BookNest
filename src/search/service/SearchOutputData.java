package search.service;

import search.entity.Book;
import java.util.List;

public class SearchOutputData {
    private final List<Book> response;
    private boolean useCaseFailed;
    public SearchOutputData(List<Book> response, boolean useCaseFailed){
        this.response = response;
        this.useCaseFailed = useCaseFailed;}
    public List<Book> getResponse(){return response;}
}
