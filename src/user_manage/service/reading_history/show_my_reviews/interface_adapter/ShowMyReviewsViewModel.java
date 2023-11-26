package user_manage.service.reading_history.show_my_reviews.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowMyReviewsViewModel extends ViewModel{
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ShowMyReviewsState state = new ShowMyReviewsState();

    public ShowMyReviewsViewModel(){
        super("show my reviews");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShowMyReviewsState getState() {
        return state;
    }
    public void setState(ShowMyReviewsState newState) {
        this.state = newState;
    }
}
