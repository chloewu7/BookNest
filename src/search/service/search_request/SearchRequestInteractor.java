package search.service.search_request;

import search.entity.Request;
import search.entity.RequestFactory;

public class SearchRequestInteractor implements SearchRequestInputBoundary{
    final SearchRequestDataAccessInterface requestDataAccessObject;
    final SearchRequestOutputBoundary requestPresenter;
    final RequestFactory requestFactory;
    public SearchRequestInteractor(SearchRequestDataAccessInterface requestDataAccessObject,
                                   SearchRequestOutputBoundary requestPresenter,
                                   RequestFactory requestFactory){
        this.requestDataAccessObject = requestDataAccessObject;
        this.requestPresenter = requestPresenter;
        this.requestFactory = requestFactory;
    }

    public void execute(SearchRequestInputData searchRequestInputData){
        if (requestDataAccessObject.validKeyword(searchRequestInputData.getKeyword())){
            Request request = requestFactory.create(searchRequestInputData.getKeyword());
            SearchRequestOutputData searchRequestOutputData = new SearchRequestOutputData(
                    request.getRequestKeyword());
            requestPresenter.sendRequestView(searchRequestOutputData);

        }
        else{
            requestPresenter.invalidKeywordView("Invalid keyword");
        }
    }
}
