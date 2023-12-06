package interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelTest {

    private ConcreteViewModel viewModel;
    private boolean propertyChangeFired;

    private class ConcreteViewModel extends ViewModel {
        private PropertyChangeSupport support;
        public ConcreteViewModel(String viewName) {
            super(viewName);
            this.support = new PropertyChangeSupport(this);
        }
        @Override
        public void firePropertyChanged() {
            support.firePropertyChange("viewName", null, getViewName());
        }

        public void addPropertyChangeListener(PropertyChangeListener listener){
            support.addPropertyChangeListener(listener);
        }

    }

    @BeforeEach
    public void setUp() {
        viewModel = new ConcreteViewModel("TestView");
        propertyChangeFired = false;
    }

    @Test
    void getViewName() {
        assertEquals("TestView", viewModel.getViewName(),
                "getViewName should return the view name set in constructor.");
    }

    @Test
    void firePropertyChanged() {
        PropertyChangeListener listener = evt -> propertyChangeFired = true;
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();
        assertTrue(propertyChangeFired, "firePropertyChanged should notify listeners");
    }

    @Test
    void addPropertyChangeListener() {
        PropertyChangeListener listener = evt -> propertyChangeFired = true;
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();
        assertTrue(propertyChangeFired, "PropertyChangeListener should have been notified after being added.");
    }
}