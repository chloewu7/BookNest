package search.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseFactoryTest {

    @Test
    void create() {
        List<Book> books = new ArrayList<>();

        books.add(new CommonBook("Title1", "Author1", "Category1", "ISBN1"));
        books.add(new CommonBook("Title2", "Author2", "Category2", "ISBN2"));

        ResponseFactory responseFactory = new NewResponseFactory();

        Response response = responseFactory.create(books);

        assertNotNull(response, "The response should not be null");

        assertTrue(response instanceof NewResponse, "The response should be an instance of NewResponse");

        assertEquals(books, response.getResponse(), "The list of books in the response should match the " +
                "list of books provided");
    }
}