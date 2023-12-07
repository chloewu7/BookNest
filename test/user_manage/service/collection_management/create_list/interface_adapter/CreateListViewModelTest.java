package user_manage.service.collection_management.create_list.interface_adapter;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateListViewModelTest {

    private static class MockPropertyChangeListener implements PropertyChangeListener {
        private String propertyName;
        private Object oldValue;
        private Object newValue;

        @Override
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            propertyName = evt.getPropertyName();
            oldValue = evt.getOldValue();
            newValue = evt.getNewValue();
        }

        public String getPropertyName() {
            return propertyName;
        }

        public Object getOldValue() {
            return oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }
    }

    @Test
    void testGettersAndSetters() {
        CreateListViewModel createListViewModel = new CreateListViewModel();
        MockPropertyChangeListener mockListener = new MockPropertyChangeListener();

        // Add the listener to the ViewModel
        createListViewModel.addPropertyChangeListener(mockListener);

        // Test initial state
        assertEquals("create list", createListViewModel.getViewName());

        // Test setters
        CreateListState newState = new CreateListState();
        newState.setUserName("testUser");
        newState.setListsName(List.of("List1", "List2"));
        newState.setCreateListError("Error creating list");

        createListViewModel.setState(newState);
    }
}
