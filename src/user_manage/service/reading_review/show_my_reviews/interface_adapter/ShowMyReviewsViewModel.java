package user_manage.service.reading_review.show_my_reviews.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowMyReviewsViewModel extends ViewModel{
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ShowMyReviewsState state = new ShowMyReviewsState();
    public static final String TITLE_LABEL = "My Reviews";
    public static final String RETURN_BUTTON_LABEL = "Return to User Center";

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
