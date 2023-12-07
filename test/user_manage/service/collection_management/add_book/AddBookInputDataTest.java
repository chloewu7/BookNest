package user_manage.service.collection_management.add_book;

import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;

import static org.junit.jupiter.api.Assertions.*;

public class AddBookInputDataTest {

    @Test
    void testGetters() {
        String userName = "testUser";
        String listName = "ReadingList";
        Book book = new CommonBook("title", "author", "novel", "123");
        AddBookInputData inputData = new AddBookInputData(userName, listName, book);

        // Verify getters
        assertEquals(userName, inputData.getUserName());
        assertEquals(listName, inputData.getListName());
        assertEquals(book, inputData.getBook());
    }

    // Add more tests if needed based on your requirements
}
