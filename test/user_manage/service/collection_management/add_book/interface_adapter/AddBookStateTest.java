package user_manage.service.collection_management.add_book.interface_adapter;

import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;

import static org.junit.jupiter.api.Assertions.*;

public class AddBookStateTest {

    @Test
    void testInitialState() {
        AddBookState addBookState = new AddBookState();

        // Verify initial state
        assertEquals("", addBookState.getUserName());
        assertEquals("", addBookState.getListName());
        assertEquals("", addBookState.getBookName());
        assertEquals("", addBookState.getBookAuthor());
        assertNull(addBookState.getBook());
        assertNull(addBookState.getAddBookSuccess());
        assertNull(addBookState.getAddBookError());
    }

    @Test
    void testSettersAndGetters() {
        AddBookState addBookState = new AddBookState();

        // Set values
        addBookState.setUserName("testUser");
        addBookState.setListName("ReadingList");
        addBookState.setBookName("BookName");
        addBookState.setBookAuthor("Author");
        Book book = new CommonBook("title", "author", "novel", "123");
        addBookState.setBook(book);
        addBookState.setAddBookSuccess("Success message");
        addBookState.setAddBookError("Error message");

        // Verify values using getters
        assertEquals("testUser", addBookState.getUserName());
        assertEquals("ReadingList", addBookState.getListName());
        assertEquals("BookName", addBookState.getBookName());
        assertEquals("Author", addBookState.getBookAuthor());
        assertEquals(book, addBookState.getBook());
        assertEquals("Success message", addBookState.getAddBookSuccess());
        assertEquals("Error message", addBookState.getAddBookError());
    }

    @Test
    void testCopyConstructor() {
        AddBookState originalState = new AddBookState();
        originalState.setUserName("testUser");
        originalState.setListName("ReadingList");
        originalState.setBookName("BookName");
        originalState.setBookAuthor("Author");
        Book book = new CommonBook("title", "author", "novel", "123");
        originalState.setBook(book);
        originalState.setAddBookSuccess("Success message");
        originalState.setAddBookError("Error message");

        AddBookState copiedState = new AddBookState(originalState);

        // Verify that the copied state has the same values
        assertEquals(originalState.getUserName(), copiedState.getUserName());
        assertEquals(originalState.getListName(), copiedState.getListName());
        assertEquals(originalState.getBookName(), copiedState.getBookName());
        assertEquals(originalState.getBookAuthor(), copiedState.getBookAuthor());
        assertEquals(originalState.getBook(), copiedState.getBook());
        assertEquals(originalState.getAddBookSuccess(), copiedState.getAddBookSuccess());
        assertEquals(originalState.getAddBookError(), copiedState.getAddBookError());
    }
}
