package user_manage.service.collection_management.show_books_in_list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowBooksInListInputDataTest {

    @Test
    void testGetters() {
        // Create an instance of ShowBooksInListInputData
        ShowBooksInListInputData inputData = new ShowBooksInListInputData("testUser", "ReadingList");

        // Verify values using getters
        assertEquals("testUser", inputData.getUserName());
        assertEquals("ReadingList", inputData.getListName());
    }
}
