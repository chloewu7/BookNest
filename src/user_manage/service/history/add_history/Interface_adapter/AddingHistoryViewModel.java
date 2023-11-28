package user_manage.service.history.add_history.Interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddingHistoryViewModel extends ViewModel {

    private AddingHistoryState state = new AddingHistoryState();

    public AddingHistoryViewModel() {
        super("adding history");
    }

    public void setState(AddingHistoryState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This method will be called by the AddingHistoryPresenter to notify the ViewModel
    // of a change in the state, which in turn will alert the view.
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AddingHistoryState getState() {
        return state;
    }
}