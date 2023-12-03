package user_manage.service.collection_management.show_all_lists.interface_adapter;

import interface_adapter.ViewModel;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowAllListsViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ShowAllListsState state = new ShowAllListsState();
    public ShowAllListsViewModel() {
        super("all collection lists");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public ShowAllListsState getState() {
        return state;
    }
    public void setState(ShowAllListsState newState) {
        this.state = newState;
    }
}
