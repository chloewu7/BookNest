package user_manage.service.history.read_history.interface_adpter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ReadingHistoryViewModel extends ViewModel {
    private ReadingHistoryState state;

    public static final String TITLE_LABEL = "History";

    public static final String RETURN_BUTTON_LABEL = "return to search";
    private final PropertyChangeSupport support;

    public ReadingHistoryViewModel() {
        super("history");
        this.state = new ReadingHistoryState();
        this.support = new PropertyChangeSupport(this);
    }



    public ReadingHistoryState getState() {
        return state;
    }



    public void setState(ReadingHistoryState state) {
        this.state = state;
    }

    /*
    public void setHistory(List<String> history) {
        state.setHistory(history);
        firePropertyChanged();
    }

    public void setErrorMessage(String errorMessage) {
        state.setErrorMessage(errorMessage);
        firePropertyChanged();
    }

     */

    // This method notifies the view of any changes in the state
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);

    }

    // Method to add a property change listener, typically the view will register itself as a listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}