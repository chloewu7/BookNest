package search.service.search_request.interface_adapter;

import search.service.search_request.SearchRequestInputBoundary;
import search.service.search_request.SearchRequestInputData;

public class SearchRequestController {
    final SearchRequestInputBoundary requestInteractor;
    public SearchRequestController(SearchRequestInputBoundary requestInteractor){
        this.requestInteractor = requestInteractor;
    }
    public void execute(String keyword){
        SearchRequestInputData searchRequestInputData = new SearchRequestInputData(
                keyword
        );
        requestInteractor.execute(searchRequestInputData);
    }
}
