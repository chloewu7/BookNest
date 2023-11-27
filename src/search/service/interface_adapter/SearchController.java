package search.service.interface_adapter;

import search.service.SearchInputBoundary;
import search.service.SearchInputData;

public class SearchController {
    final SearchInputBoundary searchInteractor;
    public SearchController(SearchInputBoundary searchInteractor){
        this.searchInteractor = searchInteractor;
    }
    public void execute(String choice, String keyword){
        SearchInputData searchInputData = new SearchInputData(choice, keyword);
    }
}
