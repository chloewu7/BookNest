package search.service;

import search.entity.RequestFactory;
import search.entity.Request;

public class SearchInteractor implements SearchInputBoundary{
    final SearchDataAccessInterface searchDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final RequestFactory requestFactory;
    public SearchInteractor(SearchDataAccessInterface searchDataAccessObject,
                            SearchOutputBoundary searchPresenter,
                            RequestFactory requestFactory){
        this.searchDataAccessObject = searchDataAccessObject;
        this.searchPresenter = searchPresenter;
        this.requestFactory = requestFactory;
    }
    public void execute(SearchInputData searchInputData){
        if(searchDataAccessObject.validKeyword(searchInputData.getKeyword())){
            Request request = requestFactory.create(searchInputData.getChoice(),
                    searchInputData.getKeyword());
        }
        else{
            searchPresenter.invalidRequestView("Invalid keyword");
        }
    }
}
