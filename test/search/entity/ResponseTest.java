package search.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    void getResponse() {
        Book book1 = new CommonBook("Title1", "Author1", "Category1", "ISBN1");

        Book book2 = new CommonBook("Title2", "Author2", "Category2", "ISBN2");

        List<Book> books = Arrays.asList(book1, book2);

        Response Response = new NewResponse(books);

        List<Book> responseBooks = Response.getResponse();

        assertNotNull(responseBooks, "Response should not be null");

        assertEquals(books.size(), responseBooks.size(), "Response should have the same number of " +
                "books as input list");

        assertArrayEquals(books.toArray(), responseBooks.toArray(), "The books in the response " +
                "should match the input books");

    }
}