package interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class ViewManagerModelTest {

    private ViewManagerModel viewManagerModel;
    private boolean propertyChangeFired;
    private String lastReceivedViewName;

    @BeforeEach
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        propertyChangeFired = false;
        lastReceivedViewName = null;

        // Add a property change listener to the viewManagerModel for testing
        PropertyChangeListener testListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                propertyChangeFired = true;
                lastReceivedViewName = (String) evt.getNewValue();
            }
        };
        viewManagerModel.addPropertyChangeListener(testListener);
    }


    @Test
    void getActiveView() {
        String expectedViewName = "TestView";

        viewManagerModel.setActiveView(expectedViewName);
        String actualViewName = viewManagerModel.getActiveView();

        assertEquals(expectedViewName, actualViewName, "Active view name should be set and retrieved correctly.");
    }

    @Test
    void setActiveView() {
        String expectedViewName = "TestView";

        viewManagerModel.setActiveView(expectedViewName);
        String actualViewName = viewManagerModel.getActiveView();

        assertEquals(expectedViewName, actualViewName, "Active view name should be set and retrieved correctly.");
    }

    @Test
    void updateView() {
        String newViewName = "NewTestView";

        viewManagerModel.updateView(newViewName);

        assertTrue(propertyChangeFired, "Property change should be fired when the view is updated.");
        assertEquals(newViewName, lastReceivedViewName, "The last received view name should be the " +
                "one set by updateView.");
    }

    @Test
    void firePropertyChanged() {
        String newViewName = "NewTestView";

        viewManagerModel.updateView(newViewName);

        assertTrue(propertyChangeFired, "Property change should be fired when the view is updated.");
        assertEquals(newViewName, lastReceivedViewName, "The last received view name should be the " +
                "one set by updateView.");
    }

    @Test
    void addPropertyChangeListener() {

    }
}