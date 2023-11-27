package search.entity;

import java.util.List;

public class NewResponse implements Response {
    private final List<Book> response;

    NewResponse(List<Book> books){
        this.response = books;
    }
    public List<Book> getResponse(){return response;}
}
