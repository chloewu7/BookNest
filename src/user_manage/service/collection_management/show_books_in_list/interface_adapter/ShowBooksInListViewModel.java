package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import interface_adapter.ViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowBooksInListViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ShowBooksInListState state = new ShowBooksInListState();
    public ShowBooksInListViewModel() {
        super("show books in list");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public ShowBooksInListState getState() {
        return state;
    }
    public void setState(ShowBooksInListState newState) {
        this.state = newState;
    }
}
