package user_manage.service.collection_management.show_all_lists.interface_adapter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ShowAllListsViewModelTest {

    @Test
    public void testDefaultConstructor() {
        ShowAllListsViewModel viewModel = new ShowAllListsViewModel();

        // Check that the ViewModel has the expected name
        assertEquals("all collection lists", viewModel.getViewName());

        // Check that the ViewModel's initial state is not null
        assertNotNull(viewModel.getState());

        // Check that the ViewModel's initial state has an empty user name and lists name
        assertEquals("", viewModel.getState().getUserName());
        assertTrue(viewModel.getState().getListsName().isEmpty());
    }

    @Test
    public void testSetAndGetState() {
        ShowAllListsViewModel viewModel = new ShowAllListsViewModel();

        // Create a new state and set it in the ViewModel
        ShowAllListsState newState = new ShowAllListsState();
        newState.setUserName("testUser");
        List<String> testLists = new ArrayList<>();
        testLists.add("list1");
        testLists.add("list2");
        newState.setListsName(testLists);
        viewModel.setState(newState);

        // Check that the ViewModel returns the correct state
        assertEquals(newState, viewModel.getState());
    }

    @Test
    public void testPropertyChangeListener() {
        ShowAllListsViewModel viewModel = new ShowAllListsViewModel();

        // Create a mock listener to observe property changes
        MockPropertyChangeListener listener = new MockPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        // Trigger a property change
        viewModel.firePropertyChanged();

        // Check that the listener was notified
        assertTrue(listener.isPropertyChanged());
    }

    private static class MockPropertyChangeListener implements PropertyChangeListener {
        private boolean propertyChanged = false;

        @Override
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            if ("state".equals(evt.getPropertyName())) {
                propertyChanged = true;
            }
        }

        public boolean isPropertyChanged() {
            return propertyChanged;
        }
    }
}
