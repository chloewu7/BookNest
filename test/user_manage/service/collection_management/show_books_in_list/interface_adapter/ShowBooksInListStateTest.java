package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowBooksInListStateTest {

    @Test
    void testSettersAndGetters() {
        ShowBooksInListState state = new ShowBooksInListState();

        // Set values using setters
        state.setUserName("testUser");
        state.setListName("ReadingList");

        Map<String, String> books = new HashMap<>();
        books.put("123", "Title1");
        books.put("456", "Title2");
        state.setBooks(books);

        // Verify values using getters
        assertEquals("testUser", state.getUserName());
        assertEquals("ReadingList", state.getListName());
        assertEquals(books, state.getBooks());
    }

    @Test
    void testCopyConstructor() {
        // Create a source state with values
        ShowBooksInListState sourceState = new ShowBooksInListState();
        sourceState.setUserName("testUser");
        sourceState.setListName("ReadingList");

        Map<String, String> books = new HashMap<>();
        books.put("123", "Title1");
        books.put("456", "Title2");
        sourceState.setBooks(books);

        // Create a new state using the copy constructor
        ShowBooksInListState newState = new ShowBooksInListState(sourceState);

        // Verify that the new state has the same values as the source state
        assertEquals(sourceState.getUserName(), newState.getUserName());
        assertEquals(sourceState.getListName(), newState.getListName());
        assertEquals(sourceState.getBooks(), newState.getBooks());
    }
}
