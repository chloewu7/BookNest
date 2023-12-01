package search.service.interface_adapter;

import interface_adapter.ViewManagerModel;
import search.entity.Book;
import search.service.SearchOutputBoundary;
import search.service.SearchOutputData;
import view.SearchView;
import view.ViewManager;

import java.util.List;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    //private ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel searchViewModel){
        //this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
    }

    public void prepareSuccessView(SearchOutputData response){
        SearchState searchState = searchViewModel.getState();
        searchState.setBooks(response.getResponse());

        this.searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();

        // viewManagerModel.setActiveView(searchState.getViewName());
        //viewManagerModel.firePropertyChanged();
    }
    public void prepareNotFoundView(String message){
        SearchState searchState = searchViewModel.getState();
        searchState.setNotFound(message);
        searchViewModel.firePropertyChanged();
    }
    public void prepareFailView(String error){
        SearchState searchState = searchViewModel.getState();
        searchState.setKeywordError(error);
        searchViewModel.firePropertyChanged();

    }

    private String convertBooksList(List<Book> books){
        StringBuilder st = new StringBuilder();
        for (Book book: books){
            st.append("Title: ").append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" Category: ")
                    .append(book.getCategory()).append(" ISBN: #").append(book.getISBN()).append(" \n\n\n");
        }
        return st.toString().trim();
    }


}
