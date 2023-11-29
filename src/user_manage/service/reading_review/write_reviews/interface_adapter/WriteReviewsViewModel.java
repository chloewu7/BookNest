package user_manage.service.reading_review.write_reviews.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WriteReviewsViewModel extends ViewModel{
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private WriteReviewsState state = new WriteReviewsState();

    public WriteReviewsViewModel(){
        super("write reviews");
    }
    public WriteReviewsState getState() {
        return state;
    }

    public void setState(WriteReviewsState newState) {
        this.state = newState;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
