package search.service.search_criteria.interface_adapter;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class SearchCriteriaViewModel {
    private SearchCriteriaState state;
    private final PropertyChangeSupport support;

    public SearchCriteriaViewModel() {
        this.state = new SearchCriteriaState();
        this.support = new PropertyChangeSupport(this);
    }

    public SearchCriteriaState getState() {
        return state;
    }

    public void setState(SearchCriteriaState state) {
        this.state = state;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
}