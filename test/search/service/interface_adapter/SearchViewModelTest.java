package search.service.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class SearchViewModelTest {

    private SearchViewModel viewModel;
    private boolean propertyChangeFired;

    @BeforeEach
    public void setUp() {
        viewModel = new SearchViewModel();
        propertyChangeFired = false;

        PropertyChangeListener testListener = evt -> propertyChangeFired = true;
        viewModel.addPropertyChangeListener(testListener);
    }

    @Test
    void setState() {
        SearchState newState = new SearchState();
        newState.setReadBookTitle("Effective Java");

        viewModel.setState(newState);

        assertEquals(newState, viewModel.getState(), "The state should be the new state set");
    }

    @Test
    void firePropertyChanged() {
        SearchState newState = new SearchState();
        newState.setReadBookTitle("Effective Java");
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        assertTrue(propertyChangeFired, "Property change should have been fired");
    }

    @Test
    void addPropertyChangeListener() {
    }

    @Test
    void getState() {
    }
}