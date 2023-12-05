package search.service.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchStateTest {

    private SearchState searchState;

    @BeforeEach
    public void setUp() {
        searchState = new SearchState();
    }

    @Test
    void copyConstructor() {
        searchState.setBooks(Arrays.asList(new CommonBook("Title1", "Author1",
                "Category1", "ISBN1")));
        searchState.setNotFound("Not found message");
        searchState.setKeywordError("Keyword error message");

        SearchState copiedState = new SearchState(searchState);

        assertEquals(searchState.getBooks(), copiedState.getBooks());
        assertEquals(searchState.getNotFound(), copiedState.getNotFound());
        assertEquals(searchState.getKeywordError(), copiedState.getKeywordError());
    }

    @Test
    void setFieldsToNull() {
        searchState.setBooks(null);
        searchState.setNotFound(null);
        searchState.setKeywordError(null);

        assertNull(searchState.getBooks());
        assertNull(searchState.getNotFound());
        assertNull(searchState.getKeywordError());
    }

    @Test
    void getBooks() {

        Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                "1234567890");

        Book book2 = new CommonBook("Clean Code", "Robert Cecil Martin", "Programming",
                "9780136083238");

        List<Book> expectedBooks = Arrays.asList(book1, book2);

        searchState.setBooks(expectedBooks);

        List<Book> actualBooks = searchState.getBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getNotFound(){
        String expectedNotFoundMessage = "Not found message";

        searchState.setNotFound(expectedNotFoundMessage);

        String actualNotFoundMessage = searchState.getNotFound();

        assertEquals(expectedNotFoundMessage, actualNotFoundMessage);

    }

    @Test
    void getKeyWordError(){
        String expectedKeywordError = "Keyword error message";

        searchState.setKeywordError(expectedKeywordError);
        String actualKeywordError = searchState.getKeywordError();

        assertEquals(expectedKeywordError, actualKeywordError);

    }

    @Test
    void getCommentBookTitle() {
        String expectedCommentBookTitle = "Book for commenting";

        searchState.setCommentBookTitle(expectedCommentBookTitle);
        String actualCommentBookTitle = searchState.getCommentBookTitle();

        assertEquals(expectedCommentBookTitle, actualCommentBookTitle);
    }

    @Test
    void getCollectedBook() {
        Book expectedCollectedBook = new CommonBook("The Great Gatsby",
                "F. Scott Fitzgerald", "Fiction",
                "1234567890");

        searchState.setCollectedBook(expectedCollectedBook);
        Book actualCollectedBook = searchState.getCollectedBook();

        assertEquals(expectedCollectedBook, actualCollectedBook);
    }

    @Test
    void getUserName() {
        String expectedUserName = "John Doe";

        searchState.setUserName(expectedUserName);
        String actualUserName = searchState.getUserName();

        assertEquals(expectedUserName, actualUserName);
    }

    @Test
    void getReadBookTitle() {
        String expectedReadBookTitle = "Book for reading";

        searchState.setReadBookTitle(expectedReadBookTitle);
        String actualReadBookTitle = searchState.getReadBookTitle();

        assertEquals(expectedReadBookTitle, actualReadBookTitle);
    }

    @Test
    void setCommentBookTitle() {
        String expectedCommentBookTitle = "Book for commenting";

        searchState.setCommentBookTitle(expectedCommentBookTitle);
        String actualCommentBookTitle = searchState.getCommentBookTitle();

        assertEquals(expectedCommentBookTitle, actualCommentBookTitle);
    }

    @Test
    void setCollectedBook() {
        Book expectedCollectedBook = new CommonBook("The Great Gatsby",
                "F. Scott Fitzgerald", "Fiction",
                "1234567890");

        searchState.setCollectedBook(expectedCollectedBook);
        Book actualCollectedBook = searchState.getCollectedBook();

        assertEquals(expectedCollectedBook, actualCollectedBook);
    }

    @Test
    void setBooks() {
        Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                "1234567890");

        Book book2 = new CommonBook("Clean Code", "Robert Cecil Martin", "Programming",
                "9780136083238");

        List<Book> expectedBooks = Arrays.asList(book1, book2);

        searchState.setBooks(expectedBooks);

        List<Book> actualBooks = searchState.getBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void setNotFound() {
        String expectedNotFoundMessage = "Not found message";

        searchState.setNotFound(expectedNotFoundMessage);

        String actualNotFoundMessage = searchState.getNotFound();

        assertEquals(expectedNotFoundMessage, actualNotFoundMessage);
    }

    @Test
    void setKeywordError() {
        String expectedKeywordError = "Keyword error message";

        searchState.setKeywordError(expectedKeywordError);
        String actualKeywordError = searchState.getKeywordError();

        assertEquals(expectedKeywordError, actualKeywordError);

    }

    @Test
    void setUserName() {
        String expectedUserName = "John Doe";

        searchState.setUserName(expectedUserName);
        String actualUserName = searchState.getUserName();

        assertEquals(expectedUserName, actualUserName);
    }

    @Test
    void setReadBookTitle() {
        String expectedReadBookTitle = "Book for reading";

        searchState.setReadBookTitle(expectedReadBookTitle);
        String actualReadBookTitle = searchState.getReadBookTitle();

        assertEquals(expectedReadBookTitle, actualReadBookTitle);
    }
}