package search.service.interface_adapter;
import interface_adapter.ViewModel;
import view.SearchView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    //public static final String Choice_Button_Label;
    //public static final String Collect_Button_Label;

    private SearchState state = new SearchState();

    public SearchViewModel(String viewName) {
        super("search");
    }

    //public SearchViewModel() To be implemented

    public void setState(SearchState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public SearchState getState(){return state;}


}
