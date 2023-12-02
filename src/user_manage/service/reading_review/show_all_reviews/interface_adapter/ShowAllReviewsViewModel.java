package user_manage.service.reading_review.show_all_reviews.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowAllReviewsViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Reviews";
    public static final String RETURN_BUTTON_LABEL = "return";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ShowAllReviewsState state = new ShowAllReviewsState();

    public ShowAllReviewsViewModel(){
        super("show all reviews");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShowAllReviewsState getState() {
        return state;
    }
    public void setState(ShowAllReviewsState newState) {
        this.state = newState;
    }

}
