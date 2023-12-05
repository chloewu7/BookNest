package search.data_access;

import search.entity.Book;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchDataAccessObjectTest {

    private TestableSearchDataAccessObject searchDataAccessObject;
    private final String sampleJsonResponse = "{\"docs\":[{\"title\":\"The Great Gatsby\",\"author_name\":" +
            "[\"F. Scott Fitzgerald\"],\"isbn\":[\"1234567890\"]}]}";

    private class TestableSearchDataAccessObject extends SearchDataAccessObject{

        private boolean shouldSimulateHttpError;
        private boolean shouldSimulateIOException;
        private boolean shouldSimulateMalformedJson;

        protected String makeApiCall(String urlString) {
            if (shouldSimulateHttpError) {
                throw new RuntimeException("HttpResponseCode: 404");
            }
            if (shouldSimulateIOException) {
                throw new RuntimeException("Error during API calling", new IOException());
            }
            if (shouldSimulateMalformedJson) {
                return "{\"malformedJson\":true}";
            }
            return sampleJsonResponse;
        }

        public void setShouldSimulateHttpError(boolean shouldSimulateHttpError) {
            this.shouldSimulateHttpError = shouldSimulateHttpError;
        }

        public void setShouldSimulateIOException(boolean shouldSimulateIOException) {
            this.shouldSimulateIOException = shouldSimulateIOException;
        }

        public void setShouldSimulateMalformedJson(boolean shouldSimulateMalformedJson) {
            this.shouldSimulateMalformedJson = shouldSimulateMalformedJson;
        }
    }

    @BeforeEach
    void setUp(){
        searchDataAccessObject = new TestableSearchDataAccessObject();
    }


    @Test
    void searchByTitleSuccess() {
        List<Book> books = searchDataAccessObject.searchByTitle("The Great Gatsby");
        assertFalse(books.isEmpty(), "The search should return at least one book");
        assertTrue(books.get(0).getTitle().contains("The Great Gatsby"), "The book title should " +
                "match the expected title");
    }

    @Test
    void searchByAuthorSuccess() {
        List<Book> books = searchDataAccessObject.searchByAuthor("F. Scott Fitzgerald");
        assertFalse(books.isEmpty(), "The search should return at least one book");
        assertTrue(books.get(0).getAuthor().contains("F. Scott Fitzgerald"),
                "The author name should match the expected author");

    }

    @Test
    void searchByCategorySuccess() {
        List<Book> books = searchDataAccessObject.searchByCategory("Fiction");
        assertFalse(books.isEmpty(), "The search should return at least one book");
        assertTrue(books.get(0).getCategory().contains("Fiction"),
                "The book category should match the expected category");
    }

    @Test
    void searchByISBNSuccess() {
        List<Book> books = searchDataAccessObject.searchByISBN("1234567890");
        assertFalse(books.isEmpty(), "The search should return at least one book");
        assertEquals("1234567890", books.get(0).getISBN(),
                "The book ISBN should match the expected ISBN");
    }

    @Test
    void searchApiCallHttpError() {

    }

    @Test
    void searchApiCallIOException() {

    }

    @Test
    void searchMalformedJsonResponse() {

    }
}