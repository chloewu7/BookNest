package user_manage.service.collection_management.add_book.interface_adapter;

import interface_adapter.ViewModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddBookViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private AddBookState state = new AddBookState();
    public AddBookViewModel() {
        super("add book to collection");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public AddBookState getState() {
        return state;
    }
    public void setState(AddBookState newState) {
        this.state = newState;
    }
}
