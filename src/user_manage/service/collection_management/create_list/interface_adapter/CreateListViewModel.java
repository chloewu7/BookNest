package user_manage.service.collection_management.create_list.interface_adapter;

import interface_adapter.ViewModel;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateListViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private CreateListState state = new CreateListState();
    public CreateListViewModel() {
        super("create list");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public CreateListState getState() {
        return state;
    }
    public void setState(CreateListState newState) {
        this.state = newState;
    }
}
