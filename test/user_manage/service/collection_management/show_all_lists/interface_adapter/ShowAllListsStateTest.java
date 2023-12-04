package user_manage.service.collection_management.show_all_lists.interface_adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
class ShowAllListsStateTest {

        @Test
        public void testDefaultConstructor() {
            ShowAllListsState state = new ShowAllListsState();

            // Check that the default values are as expected
            assertEquals("", state.getUserName());
            assertTrue(state.getListsName().isEmpty());
        }

        @Test
        public void testCopyConstructor() {
            // Create a source state with some values
            ShowAllListsState sourceState = new ShowAllListsState();
            sourceState.setUserName("testUser");
            sourceState.setListsName(Arrays.asList("List1", "List2"));

            // Create a new state by copying the source state
            ShowAllListsState copiedState = new ShowAllListsState(sourceState);

            // Check that the copied state has the same values
            assertEquals(sourceState.getUserName(), copiedState.getUserName());
            assertIterableEquals(sourceState.getListsName(), copiedState.getListsName());
        }

        @Test
        public void testSettersAndGetters() {
            ShowAllListsState state = new ShowAllListsState();

            // Set values using setters
            state.setUserName("newUser");
            state.setListsName(Arrays.asList("NewList1", "NewList2"));

            // Check that the getters return the expected values
            assertEquals("newUser", state.getUserName());
            assertIterableEquals(Arrays.asList("NewList1", "NewList2"), state.getListsName());
        }

}