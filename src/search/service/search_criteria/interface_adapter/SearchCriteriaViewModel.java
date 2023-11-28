package search.service.search_criteria.interface_adapter;


import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class SearchCriteriaViewModel extends ViewModel {
    private SearchCriteriaState state;
    private final PropertyChangeSupport support;
    private final String viewName;

    public SearchCriteriaViewModel(String viewName) {
        super("Search Criteria");
        this.state = new SearchCriteriaState();
        this.support = new PropertyChangeSupport(this);
        this.viewName = viewName;
    }

    public static SearchCriteriaState getState() {
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
    public String getViewName() {
        return this.viewName;
    }
}