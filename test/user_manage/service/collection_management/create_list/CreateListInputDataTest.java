package user_manage.service.collection_management.create_list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateListInputDataTest {

    @Test
    void testGetters() {
        String userName = "testUser";
        String listName = "TestList";
        CreateListInputData createListInputData = new CreateListInputData(userName, listName);

        // Test getters
        assertEquals(userName, createListInputData.getUserName());
        assertEquals(listName, createListInputData.getListName());
    }
}
