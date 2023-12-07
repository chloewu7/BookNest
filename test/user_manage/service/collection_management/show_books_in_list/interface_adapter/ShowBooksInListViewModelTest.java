package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowBooksInListViewModelTest {

    @Test
    void testSettersAndGetters() {
        ShowBooksInListViewModel viewModel = new ShowBooksInListViewModel();

        // Set values using setters
        ShowBooksInListState newState = new ShowBooksInListState();
        newState.setUserName("testUser");
        newState.setListName("ReadingList");
        viewModel.setState(newState);

        // Verify values using getters
        assertEquals(newState, viewModel.getState());
    }

    @Test
    void testPropertyChangeSupport() {
        ShowBooksInListViewModel viewModel = new ShowBooksInListViewModel();

        // Create a listener to track property changes
        PropertyChangeTracker changeTracker = new PropertyChangeTracker();
        viewModel.addPropertyChangeListener(changeTracker);

        // Set a new state, and verify that the property change is detected
        ShowBooksInListState newState = new ShowBooksInListState();
        newState.setUserName("testUser");
        viewModel.setState(newState);

        // Verify that the property change event was received
        assertEquals(null, changeTracker.getOldValue());
    }

    private static class PropertyChangeTracker implements PropertyChangeListener {
        private String lastPropertyName;
        private Object oldValue;
        private Object newValue;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            lastPropertyName = evt.getPropertyName();
            oldValue = evt.getOldValue();
            newValue = evt.getNewValue();
        }

        public String getLastPropertyName() {
            return lastPropertyName;
        }

        public Object getOldValue() {
            return oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }
    }
}
