package user_manage.service.collection_management.add_book.interface_adapter;

import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static org.junit.jupiter.api.Assertions.*;

public class AddBookViewModelTest {

    @Test
    void testInitialState() {
        AddBookViewModel viewModel = new AddBookViewModel();

        // Verify initial state
        assertNotNull(viewModel.getState());
        assertEquals("add book to collection", viewModel.getViewName());
    }

    @Test
    void testSetState() {
        AddBookViewModel viewModel = new AddBookViewModel();
        AddBookState newState = new AddBookState();
        newState.setUserName("testUser");
        newState.setListName("ReadingList");

        // Set state and verify
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    @Test
    void testPropertyChangeNotification() {
        AddBookViewModel viewModel = new AddBookViewModel();
        TestPropertyChangeListener listener = new TestPropertyChangeListener();

        // Add listener and verify initial state
        viewModel.addPropertyChangeListener(listener);
        assertNull(listener.lastEvent);

        // Trigger property change and verify listener notification
        viewModel.firePropertyChanged();
        assertNotNull(listener.lastEvent);
        assertEquals("state", listener.lastEvent.getPropertyName());
        assertNull(listener.lastEvent.getOldValue());
        assertNotNull(listener.lastEvent.getNewValue());
        assertEquals(viewModel.getState(), listener.lastEvent.getNewValue());
    }

    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private PropertyChangeEvent lastEvent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            lastEvent = evt;
        }
    }
}
