package search.entity;

import java.util.List;

public class NewResponseFactory implements ResponseFactory {
    public Response create(List<Book> response){
        return new NewResponse(response);}
}
