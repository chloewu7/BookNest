package app;

import search.data_access.SearchDataAccessObject;
import search.entity.NewResponseFactory;
import search.entity.ResponseFactory;
import search.service.SearchDataAccessInterface;
import search.service.SearchInputData;
import search.service.SearchInteractor;
import search.service.SearchOutputBoundary;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchViewModel;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory(){}


    public static SearchInteractor createSearchInteractor(SearchViewModel searchViewModel) {
        SearchDataAccessInterface searchDAO = new SearchDataAccessObject();
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        ResponseFactory responseFactory = new NewResponseFactory();
        SearchInteractor searchInteractor = new SearchInteractor(searchDAO, searchPresenter, responseFactory);

        return searchInteractor;

    }
}

