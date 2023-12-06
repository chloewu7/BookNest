package view.UserCenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class UserCenterViewModelTest {

    private UserCenterViewModel viewModel;
    private boolean propertyChangeFired;

    @BeforeEach
    public void setUp() {
        viewModel = new UserCenterViewModel();
        propertyChangeFired = false;

        PropertyChangeListener testListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                propertyChangeFired = true;
            }
        };
        viewModel.addPropertyChangeListener(testListener);
    }

    @Test
    void setState() {
        UserCenterState newState = new UserCenterState();
        newState.setUsername("testUser");

        viewModel.setState(newState);

        assertEquals(newState, viewModel.getState(), "The state should be the new state set");
    }

    @Test
    void getState() {
        UserCenterState newState = new UserCenterState();
        newState.setUsername("testUser");

        viewModel.setState(newState);

        assertEquals(newState, viewModel.getState(), "The state should be the new state set");
    }

    @Test
    void firePropertyChanged() {

        UserCenterState newState = new UserCenterState();
        newState.setUsername("testUser");
        viewModel.setState(newState);

        viewModel.firePropertyChanged();

        assertTrue(propertyChangeFired, "Property change should have been fired");
    }


    @Test
    void addPropertyChangeListener() {
    }
}