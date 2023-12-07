package user_manage.service.collection_management.create_list.interface_adapter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateListStateTest {

    @Test
    void testGettersAndSetters() {
        CreateListState createListState = new CreateListState();

        // Test initial state
        assertEquals("", createListState.getUserName());
        assertEquals(new ArrayList<>(), createListState.getListsName());
        assertNull(createListState.getCreateListError());

        // Test setters
        createListState.setUserName("testUser");
        createListState.setListsName(List.of("List1", "List2"));
        createListState.setCreateListError("Error creating list");

        // Test getters
        assertEquals("testUser", createListState.getUserName());
        assertEquals(List.of("List1", "List2"), createListState.getListsName());
        assertEquals("Error creating list", createListState.getCreateListError());
    }
}
